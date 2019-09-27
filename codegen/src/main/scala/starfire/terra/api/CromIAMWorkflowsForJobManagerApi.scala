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

import java.util.Date
import starfire.terra.model.LabelsResponse
import starfire.terra.model.WorkflowAbortResponse
import starfire.terra.model.WorkflowMetadataResponse
import starfire.terra.model.WorkflowQueryParameter
import starfire.terra.model.WorkflowQueryResponse
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

class CromIAMWorkflowsForJobManagerApi(
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
  val helper = new CromIAMWorkflowsForJobManagerApiAsyncHelper(client, config)

  /**
   * Abort a workflow based on workflow id
   * 
   *
   * @param version API Version 
   * @param id Workflow ID 
   * @return WorkflowAbortResponse
   */
  def apiWorkflowsVersionIdAbortPost(version: String, id: String): Option[WorkflowAbortResponse] = {
    val await = Try(Await.result(apiWorkflowsVersionIdAbortPostAsync(version, id), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * Abort a workflow based on workflow id asynchronously
   * 
   *
   * @param version API Version 
   * @param id Workflow ID 
   * @return Future(WorkflowAbortResponse)
   */
  def apiWorkflowsVersionIdAbortPostAsync(version: String, id: String): Future[WorkflowAbortResponse] = {
      helper.apiWorkflowsVersionIdAbortPost(version, id)
  }

  /**
   * Get backend (e.g. PAPI) metadata for a job
   * 
   *
   * @param version API Version 
   * @param id Workflow ID 
   * @param backendId Backend ID, must be a job that is part of the workflow 
   * @return void
   */
  def apiWorkflowsVersionIdBackendMetadataBackendIdGet(version: String, id: String, backendId: String) = {
    val await = Try(Await.result(apiWorkflowsVersionIdBackendMetadataBackendIdGetAsync(version, id, backendId), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * Get backend (e.g. PAPI) metadata for a job asynchronously
   * 
   *
   * @param version API Version 
   * @param id Workflow ID 
   * @param backendId Backend ID, must be a job that is part of the workflow 
   * @return Future(void)
   */
  def apiWorkflowsVersionIdBackendMetadataBackendIdGetAsync(version: String, id: String, backendId: String) = {
      helper.apiWorkflowsVersionIdBackendMetadataBackendIdGet(version, id, backendId)
  }

  /**
   * Add new labels or update values for existing label keys by workflow id.
   * 
   *
   * @param version API Version 
   * @param id Workflow ID 
   * @param labels Custom labels submitted as JSON. Example: {\&quot;key-1\&quot;:\&quot;value-1\&quot;,\&quot;key-2\&quot;:\&quot;value-2\&quot;}  
   * @return LabelsResponse
   */
  def apiWorkflowsVersionIdLabelsPatch(version: String, id: String, labels: Any): Option[LabelsResponse] = {
    val await = Try(Await.result(apiWorkflowsVersionIdLabelsPatchAsync(version, id, labels), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * Add new labels or update values for existing label keys by workflow id. asynchronously
   * 
   *
   * @param version API Version 
   * @param id Workflow ID 
   * @param labels Custom labels submitted as JSON. Example: {\&quot;key-1\&quot;:\&quot;value-1\&quot;,\&quot;key-2\&quot;:\&quot;value-2\&quot;}  
   * @return Future(LabelsResponse)
   */
  def apiWorkflowsVersionIdLabelsPatchAsync(version: String, id: String, labels: Any): Future[LabelsResponse] = {
      helper.apiWorkflowsVersionIdLabelsPatch(version, id, labels)
  }

  /**
   * Query for workflow and call-level metadata for a specified workflow
   * 
   *
   * @param version API Version 
   * @param id Workflow ID 
   * @param includeKey When specified key(s) to include from the metadata. Matches any key starting with the value. May not be used with excludeKey.  (optional)
   * @param excludeKey When specified key(s) to exclude from the metadata. Matches any key starting with the value. May not be used with includeKey.  (optional)
   * @param expandSubWorkflows When true, metadata for sub workflows will be fetched and inserted automatically in the metadata response.  (optional, default to false)
   * @return WorkflowMetadataResponse
   */
  def apiWorkflowsVersionIdMetadataGet(version: String, id: String, includeKey: Option[List[String]] = None, excludeKey: Option[List[String]] = None, expandSubWorkflows: Option[Boolean] = Option(false)): Option[WorkflowMetadataResponse] = {
    val await = Try(Await.result(apiWorkflowsVersionIdMetadataGetAsync(version, id, includeKey, excludeKey, expandSubWorkflows), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * Query for workflow and call-level metadata for a specified workflow asynchronously
   * 
   *
   * @param version API Version 
   * @param id Workflow ID 
   * @param includeKey When specified key(s) to include from the metadata. Matches any key starting with the value. May not be used with excludeKey.  (optional)
   * @param excludeKey When specified key(s) to exclude from the metadata. Matches any key starting with the value. May not be used with includeKey.  (optional)
   * @param expandSubWorkflows When true, metadata for sub workflows will be fetched and inserted automatically in the metadata response.  (optional, default to false)
   * @return Future(WorkflowMetadataResponse)
   */
  def apiWorkflowsVersionIdMetadataGetAsync(version: String, id: String, includeKey: Option[List[String]] = None, excludeKey: Option[List[String]] = None, expandSubWorkflows: Option[Boolean] = Option(false)): Future[WorkflowMetadataResponse] = {
      helper.apiWorkflowsVersionIdMetadataGet(version, id, includeKey, excludeKey, expandSubWorkflows)
  }

  /**
   * Query workflows by start dates, end dates, names, ids, or statuses.
   * 
   *
   * @param version API Version 
   * @param start Returns only workflows with an equal or later start datetime.  Can be specified at most once. If both start and end date are specified, start date must be before or equal to end date.  (optional)
   * @param end Returns only workflows with an equal or earlier end datetime.  Can be specified at most once. If both start and end date are specified, start date must be before or equal to end date.  (optional)
   * @param status Returns only workflows with the specified status.  If specified multiple times, returns workflows in any of the specified statuses.  (optional)
   * @param name Returns only workflows with the specified name.  If specified multiple times, returns workflows with any of the specified names.  (optional)
   * @param id Returns only workflows with the specified workflow id.  If specified multiple times, returns workflows with any of the specified workflow ids.  (optional)
   * @return WorkflowQueryResponse
   */
  def apiWorkflowsVersionQueryGet(version: String, start: Option[Date] = None, end: Option[Date] = None, status: Option[List[String]] = None, name: Option[List[String]] = None, id: Option[List[String]] = None): Option[WorkflowQueryResponse] = {
    val await = Try(Await.result(apiWorkflowsVersionQueryGetAsync(version, start, end, status, name, id), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * Query workflows by start dates, end dates, names, ids, or statuses. asynchronously
   * 
   *
   * @param version API Version 
   * @param start Returns only workflows with an equal or later start datetime.  Can be specified at most once. If both start and end date are specified, start date must be before or equal to end date.  (optional)
   * @param end Returns only workflows with an equal or earlier end datetime.  Can be specified at most once. If both start and end date are specified, start date must be before or equal to end date.  (optional)
   * @param status Returns only workflows with the specified status.  If specified multiple times, returns workflows in any of the specified statuses.  (optional)
   * @param name Returns only workflows with the specified name.  If specified multiple times, returns workflows with any of the specified names.  (optional)
   * @param id Returns only workflows with the specified workflow id.  If specified multiple times, returns workflows with any of the specified workflow ids.  (optional)
   * @return Future(WorkflowQueryResponse)
   */
  def apiWorkflowsVersionQueryGetAsync(version: String, start: Option[Date] = None, end: Option[Date] = None, status: Option[List[String]] = None, name: Option[List[String]] = None, id: Option[List[String]] = None): Future[WorkflowQueryResponse] = {
      helper.apiWorkflowsVersionQueryGet(version, start, end, status, name, id)
  }

  /**
   * Query workflows by start dates, end dates, names, ids, or statuses.
   * 
   *
   * @param version API version 
   * @param parameters Same query parameters as GET /query endpoint, submitted as a json list. Example: [{\&quot;status\&quot;:\&quot;Success\&quot;},{\&quot;status\&quot;:\&quot;Failed\&quot;}]  
   * @return WorkflowQueryResponse
   */
  def apiWorkflowsVersionQueryPost(version: String, parameters: List[WorkflowQueryParameter]): Option[WorkflowQueryResponse] = {
    val await = Try(Await.result(apiWorkflowsVersionQueryPostAsync(version, parameters), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * Query workflows by start dates, end dates, names, ids, or statuses. asynchronously
   * 
   *
   * @param version API version 
   * @param parameters Same query parameters as GET /query endpoint, submitted as a json list. Example: [{\&quot;status\&quot;:\&quot;Success\&quot;},{\&quot;status\&quot;:\&quot;Failed\&quot;}]  
   * @return Future(WorkflowQueryResponse)
   */
  def apiWorkflowsVersionQueryPostAsync(version: String, parameters: List[WorkflowQueryParameter]): Future[WorkflowQueryResponse] = {
      helper.apiWorkflowsVersionQueryPost(version, parameters)
  }

}

class CromIAMWorkflowsForJobManagerApiAsyncHelper(client: TransportClient, config: SwaggerConfig) extends ApiClient(client, config) {

  def apiWorkflowsVersionIdAbortPost(version: String,
    id: String)(implicit reader: ClientResponseReader[WorkflowAbortResponse]): Future[WorkflowAbortResponse] = {
    // create path and map variables
    val path = (addFmt("/api/workflows/{version}/{id}/abort")
      replaceAll("\\{" + "version" + "\\}", version.toString)
      replaceAll("\\{" + "id" + "\\}", id.toString))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (version == null) throw new Exception("Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdAbortPost")

    if (id == null) throw new Exception("Missing required parameter 'id' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdAbortPost")


    val resFuture = client.submit("POST", path, queryParams.toMap, headerParams.toMap, "")
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }

  def apiWorkflowsVersionIdBackendMetadataBackendIdGet(version: String,
    id: String,
    backendId: String)(implicit reader: ClientResponseReader[Unit]): Future[Unit] = {
    // create path and map variables
    val path = (addFmt("/api/workflows/{version}/{id}/backend/metadata/{backendId}")
      replaceAll("\\{" + "version" + "\\}", version.toString)
      replaceAll("\\{" + "id" + "\\}", id.toString)
      replaceAll("\\{" + "backendId" + "\\}", backendId.toString))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (version == null) throw new Exception("Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdBackendMetadataBackendIdGet")

    if (id == null) throw new Exception("Missing required parameter 'id' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdBackendMetadataBackendIdGet")

    if (backendId == null) throw new Exception("Missing required parameter 'backendId' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdBackendMetadataBackendIdGet")


    val resFuture = client.submit("GET", path, queryParams.toMap, headerParams.toMap, "")
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }

  def apiWorkflowsVersionIdLabelsPatch(version: String,
    id: String,
    labels: Any)(implicit reader: ClientResponseReader[LabelsResponse], writer: RequestWriter[Any]): Future[LabelsResponse] = {
    // create path and map variables
    val path = (addFmt("/api/workflows/{version}/{id}/labels")
      replaceAll("\\{" + "version" + "\\}", version.toString)
      replaceAll("\\{" + "id" + "\\}", id.toString))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (version == null) throw new Exception("Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdLabelsPatch")

    if (id == null) throw new Exception("Missing required parameter 'id' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdLabelsPatch")


    val resFuture = client.submit("PATCH", path, queryParams.toMap, headerParams.toMap, writer.write(labels))
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }

  def apiWorkflowsVersionIdMetadataGet(version: String,
    id: String,
    includeKey: Option[List[String]] = None,
    excludeKey: Option[List[String]] = None,
    expandSubWorkflows: Option[Boolean] = Option(false)
    )(implicit reader: ClientResponseReader[WorkflowMetadataResponse]): Future[WorkflowMetadataResponse] = {
    // create path and map variables
    val path = (addFmt("/api/workflows/{version}/{id}/metadata")
      replaceAll("\\{" + "version" + "\\}", version.toString)
      replaceAll("\\{" + "id" + "\\}", id.toString))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (version == null) throw new Exception("Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdMetadataGet")

    if (id == null) throw new Exception("Missing required parameter 'id' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdMetadataGet")

    includeKey match {
      case Some(param) => queryParams += "includeKey" -> param.toString
      case _ => queryParams
    }
    excludeKey match {
      case Some(param) => queryParams += "excludeKey" -> param.toString
      case _ => queryParams
    }
    expandSubWorkflows match {
      case Some(param) => queryParams += "expandSubWorkflows" -> param.toString
      case _ => queryParams
    }

    val resFuture = client.submit("GET", path, queryParams.toMap, headerParams.toMap, "")
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }

  def apiWorkflowsVersionQueryGet(version: String,
    start: Option[Date] = None,
    end: Option[Date] = None,
    status: Option[List[String]] = None,
    name: Option[List[String]] = None,
    id: Option[List[String]] = None
    )(implicit reader: ClientResponseReader[WorkflowQueryResponse]): Future[WorkflowQueryResponse] = {
    // create path and map variables
    val path = (addFmt("/api/workflows/{version}/query")
      replaceAll("\\{" + "version" + "\\}", version.toString))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (version == null) throw new Exception("Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionQueryGet")

    start match {
      case Some(param) => queryParams += "start" -> param.toString
      case _ => queryParams
    }
    end match {
      case Some(param) => queryParams += "end" -> param.toString
      case _ => queryParams
    }
    status match {
      case Some(param) => queryParams += "status" -> param.toString
      case _ => queryParams
    }
    name match {
      case Some(param) => queryParams += "name" -> param.toString
      case _ => queryParams
    }
    id match {
      case Some(param) => queryParams += "id" -> param.toString
      case _ => queryParams
    }

    val resFuture = client.submit("GET", path, queryParams.toMap, headerParams.toMap, "")
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }

  def apiWorkflowsVersionQueryPost(version: String,
    parameters: List[WorkflowQueryParameter])(implicit reader: ClientResponseReader[WorkflowQueryResponse], writer: RequestWriter[List[WorkflowQueryParameter]]): Future[WorkflowQueryResponse] = {
    // create path and map variables
    val path = (addFmt("/api/workflows/{version}/query")
      replaceAll("\\{" + "version" + "\\}", version.toString))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (version == null) throw new Exception("Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionQueryPost")

    if (parameters == null) throw new Exception("Missing required parameter 'parameters' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionQueryPost")

    val resFuture = client.submit("POST", path, queryParams.toMap, headerParams.toMap, writer.write(parameters))
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }


}