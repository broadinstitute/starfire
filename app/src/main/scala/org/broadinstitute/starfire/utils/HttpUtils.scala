package org.broadinstitute.starfire.utils

import better.files.File
import org.broadinstitute.starfire.app.silk.SilkValue.SilkObjectValue
import org.broadinstitute.starfire.auth.OAuthUtils
import org.broadinstitute.starfire.util.SnagOld
import sttp.client.{Identity, NothingT, Request, Response, ResponseError, SttpBackend}
import scala.jdk.CollectionConverters._

import scala.util.{Failure, Success}

object HttpUtils {

  def statusMessage(response: Response[_]): String = {
    val statusCode = response.code.code
    val category = (statusCode / 100) match {
      case 1 => "Information"
      case 2 => "Success"
      case 3 => "Redirect"
      case 4 => "Client error"
      case 5 => "Server error"
      case _ => "Unknown category"
    }
    s"HTTP $category $statusCode: ${response.statusText}"
  }

  def send[T](request: Request[Either[ResponseError[io.circe.Error], T], Nothing]
             )(implicit backend: SttpBackend[Identity, Nothing, NothingT]): Either[SnagOld, T] = {
    val response = request.send()
    response.body match {
      case Left(responseError) =>
        // responseError.printStackTrace()
        val originalMessage = responseError.getMessage
        val message = if (originalMessage != null) {
          originalMessage
        } else {
          statusMessage(response)
        }
        Left(SnagOld(message))
      case Right(value) => Right(value)
    }
  }

  def authorize[T](request: Request[Either[ResponseError[io.circe.Error], T], Nothing],
                   keyFile: File,
                   scopes: Iterable[String]):
  Either[SnagOld, Request[Either[ResponseError[io.circe.Error], T], Nothing]] = {
    OAuthUtils.readServiceAccountCredentials(keyFile) match {
      case Failure(exception) => Left(SnagOld("Could not load service account key file: " + exception.getMessage))
      case Success(unscopedServiceAccountCredentials) =>
        val serviceAccountCredentials = unscopedServiceAccountCredentials.createScoped(scopes.asJavaCollection)
        val authorizedRequest = OAuthUtils.addAccessToken(request, serviceAccountCredentials)
        Right(authorizedRequest)
    }
  }

  def sendAuthorized[T](request: Request[Either[ResponseError[io.circe.Error], T], Nothing],
                        keyFile: File,
                        scopes: Iterable[String]
                   )(implicit backend: SttpBackend[Identity, Nothing, NothingT]): Either[SnagOld, T] = {
    authorize(request, keyFile, scopes) match {
      case Left(error) => Left(error)
      case Right(authorizedRequest) => send(authorizedRequest)
    }
  }
}