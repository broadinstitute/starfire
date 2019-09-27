/**
 * FireCloud
 * Genome analysis execution service. 
 *
 * OpenAPI spec version: 0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package starfire.terra.api

import java.text.SimpleDateFormat

import starfire.terra.model.ExecutionEngineVersion
import starfire.terra.model.OrchestrationVersion
import io.swagger.client.{ApiInvoker, ApiException}

import com.sun.jersey.multipart.FormDataMultiPart
import com.sun.jersey.multipart.file.FileDataBodyPart

import javax.ws.rs.core.MediaType

import java.io.File
import java.util.Date
import java.util.TimeZone

import scala.collection.mutable.HashMap

import com.wordnik.swagger.client._
import scala.concurrent.Future
import collection.mutable

import java.net.URI

import com.wordnik.swagger.client.ClientResponseReaders.Json4sFormatsReader._
import com.wordnik.swagger.client.RequestWriters.Json4sFormatsWriter._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

import org.json4s._

class VersionApi(
  val defBasePath: String = "https://localhost",
  defApiInvoker: ApiInvoker = ApiInvoker
) {
  private lazy val dateTimeFormatter = {
    val formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"))
    formatter
  }
  private val dateFormatter = {
    val formatter = new SimpleDateFormat("yyyy-MM-dd")
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"))
    formatter
  }
  implicit val formats = new org.json4s.DefaultFormats {
    override def dateFormatter = dateTimeFormatter
  }
  implicit val stringReader: ClientResponseReader[String] = ClientResponseReaders.StringReader
  implicit val unitReader: ClientResponseReader[Unit] = ClientResponseReaders.UnitReader
  implicit val jvalueReader: ClientResponseReader[JValue] = ClientResponseReaders.JValueReader
  implicit val jsonReader: ClientResponseReader[Nothing] = JsonFormatsReader
  implicit val stringWriter: RequestWriter[String] = RequestWriters.StringWriter
  implicit val jsonWriter: RequestWriter[Nothing] = JsonFormatsWriter

  var basePath: String = defBasePath
  var apiInvoker: ApiInvoker = defApiInvoker

  def addHeader(key: String, value: String): mutable.HashMap[String, String] = {
    apiInvoker.defaultHeaders += key -> value
  }

  val config: SwaggerConfig = SwaggerConfig.forUrl(new URI(defBasePath))
  val client = new RestClient(config)
  val helper = new VersionApiAsyncHelper(client, config)

  /**
   * Returns the currently deployed version of FireCloud&#39;s execution engine
   * 
   *
   * @return ExecutionEngineVersion
   */
  def executionEngineVersion(): Option[ExecutionEngineVersion] = {
    val await = Try(Await.result(executionEngineVersionAsync(), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * Returns the currently deployed version of FireCloud&#39;s execution engine asynchronously
   * 
   *
   * @return Future(ExecutionEngineVersion)
   */
  def executionEngineVersionAsync(): Future[ExecutionEngineVersion] = {
      helper.executionEngineVersion()
  }

  /**
   * Returns the currently deployed version of this service.
   * 
   *
   * @return OrchestrationVersion
   */
  def orchestrationVersion(): Option[OrchestrationVersion] = {
    val await = Try(Await.result(orchestrationVersionAsync(), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * Returns the currently deployed version of this service. asynchronously
   * 
   *
   * @return Future(OrchestrationVersion)
   */
  def orchestrationVersionAsync(): Future[OrchestrationVersion] = {
      helper.orchestrationVersion()
  }

}

class VersionApiAsyncHelper(client: TransportClient, config: SwaggerConfig) extends ApiClient(client, config) {

  def executionEngineVersion()(implicit reader: ClientResponseReader[ExecutionEngineVersion]): Future[ExecutionEngineVersion] = {
    // create path and map variables
    val path = (addFmt("/version/executionEngine"))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]


    val resFuture = client.submit("GET", path, queryParams.toMap, headerParams.toMap, "")
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }

  def orchestrationVersion()(implicit reader: ClientResponseReader[OrchestrationVersion]): Future[OrchestrationVersion] = {
    // create path and map variables
    val path = (addFmt("/version"))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]


    val resFuture = client.submit("GET", path, queryParams.toMap, headerParams.toMap, "")
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }


}
