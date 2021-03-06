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

package org.broadinstitute.starfire.api

import java.util.Date
import java.util.Date._
import io.circe.Json
import io.circe.Json._
import org.broadinstitute.starfire.model.LabelsResponse
import org.broadinstitute.starfire.model.LabelsResponse._
import org.broadinstitute.starfire.model.WorkflowAbortResponse
import org.broadinstitute.starfire.model.WorkflowAbortResponse._
import org.broadinstitute.starfire.model.WorkflowMetadataResponse
import org.broadinstitute.starfire.model.WorkflowMetadataResponse._
import org.broadinstitute.starfire.model.WorkflowQueryParameter
import org.broadinstitute.starfire.model.WorkflowQueryParameter._
import org.broadinstitute.starfire.model.WorkflowQueryResponse
import org.broadinstitute.starfire.model.WorkflowQueryResponse._

import sttp.client._
import sttp.client.circe._
import io.circe.generic.auto._

import io.swagger.sttp.utils.Decoders._
import io.swagger.sttp.utils.Encoders._
import io.swagger.sttp.utils.SttpUtils.Implicits._

object CromIAMWorkflowsForJobManagerApi {

  /**
   * Abort a workflow based on workflow id
   * 
   *
   * @param version API Version 
   * @param id Workflow ID 
   * @return Option[WorkflowAbortResponse]
   */
  def apiWorkflowsVersionIdAbortPost(
    version: String,
    id: String): Request[Either[ResponseError[io.circe.Error],WorkflowAbortResponse],Nothing] = {
    assert(version != null, "Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdAbortPost")
    assert(id != null, "Missing required parameter 'id' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdAbortPost")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workflows/${version}/${id}/abort")
      .response(asJson[WorkflowAbortResponse])
  }

  /**
   * Get backend (e.g. PAPI) metadata for a job
   * 
   *
   * @param version API Version 
   * @param id Workflow ID 
   * @param backendId Backend ID, must be a job that is part of the workflow 
   * @return Option[io.circe.Json]
   */
  def apiWorkflowsVersionIdBackendMetadataBackendIdGet(
    version: String,
    id: String,
    backendId: String): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(version != null, "Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdBackendMetadataBackendIdGet")
    assert(id != null, "Missing required parameter 'id' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdBackendMetadataBackendIdGet")
    assert(backendId != null, "Missing required parameter 'backendId' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdBackendMetadataBackendIdGet")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workflows/${version}/${id}/backend/metadata/${backendId}")
      .response(asJson[io.circe.Json])
  }

  /**
   * Add new labels or update values for existing label keys by workflow id.
   * 
   *
   * @param version API Version 
   * @param id Workflow ID 
   * @param labels Custom labels submitted as JSON. Example: {\&quot;key-1\&quot;:\&quot;value-1\&quot;,\&quot;key-2\&quot;:\&quot;value-2\&quot;}  
   * @return Option[LabelsResponse]
   */
  def apiWorkflowsVersionIdLabelsPatch(
    version: String,
    id: String,
    labels: Json): Request[Either[ResponseError[io.circe.Error],LabelsResponse],Nothing] = {
    assert(version != null, "Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdLabelsPatch")
    assert(id != null, "Missing required parameter 'id' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdLabelsPatch")
    assert(labels != null, "Missing required parameter 'labels' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdLabelsPatch")

    basicRequest
      .patch(uri"https://api.firecloud.org/api/workflows/${version}/${id}/labels")
      .body(labels)
      .response(asJson[LabelsResponse])
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
   * @return Option[WorkflowMetadataResponse]
   */
  def apiWorkflowsVersionIdMetadataGet(
    version: String,
    id: String,
    includeKey: Option[List[String]] = None,
    excludeKey: Option[List[String]] = None,
    expandSubWorkflows: Option[Boolean] = Option(false)
    ): Request[Either[ResponseError[io.circe.Error],WorkflowMetadataResponse],Nothing] = {
    assert(version != null, "Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdMetadataGet")
    assert(id != null, "Missing required parameter 'id' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionIdMetadataGet")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workflows/${version}/${id}/metadata?includeKey=${includeKey}?excludeKey=${excludeKey}?expandSubWorkflows=${expandSubWorkflows}")
      .response(asJson[WorkflowMetadataResponse])
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
   * @return Option[WorkflowQueryResponse]
   */
  def apiWorkflowsVersionQueryGet(
    version: String,
    start: Option[Date] = None,
    end: Option[Date] = None,
    status: Option[List[String]] = None,
    name: Option[List[String]] = None,
    id: Option[List[String]] = None
    ): Request[Either[ResponseError[io.circe.Error],WorkflowQueryResponse],Nothing] = {
    assert(version != null, "Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionQueryGet")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workflows/${version}/query?start=${start}?end=${end}?status=${status}?name=${name}?id=${id}")
      .response(asJson[WorkflowQueryResponse])
  }

  /**
   * Query workflows by start dates, end dates, names, ids, or statuses.
   * 
   *
   * @param version API version 
   * @param parameters Same query parameters as GET /query endpoint, submitted as a json list. Example: [{\&quot;status\&quot;:\&quot;Success\&quot;},{\&quot;status\&quot;:\&quot;Failed\&quot;}]  
   * @return Option[WorkflowQueryResponse]
   */
  def apiWorkflowsVersionQueryPost(
    version: String,
    parameters: List[WorkflowQueryParameter]): Request[Either[ResponseError[io.circe.Error],WorkflowQueryResponse],Nothing] = {
    assert(version != null, "Missing required parameter 'version' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionQueryPost")
    assert(parameters != null, "Missing required parameter 'parameters' when calling CromIAMWorkflowsForJobManagerApi->apiWorkflowsVersionQueryPost")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workflows/${version}/query")
      .body(parameters)
      .response(asJson[WorkflowQueryResponse])
  }

}

