package org.broadinstitute.starfire.utils

import org.broadinstitute.starfire.app.silk.SilkError
import org.broadinstitute.starfire.app.silk.SilkValue.SilkObjectValue
import sttp.client.{Identity, NothingT, Request, ResponseError, SttpBackend}

object HttpUtils {

  def send[T](request: Request[Either[ResponseError[io.circe.Error], T], Nothing]
             )(implicit backend: SttpBackend[Identity, Nothing, NothingT]): Either[SilkError, T] = {
    request.send().body match {
      case Left(responseError) => Left(SilkError(responseError.getMessage))
      case Right(value) => Right(value)
    }
  }

}
