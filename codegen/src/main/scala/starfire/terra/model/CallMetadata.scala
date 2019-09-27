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

package starfire.terra.model

import java.util.Date

case class CallMetadata (
  // map from input names onto resolved values
  inputs: Option[Any] = None,
  // status of call
  executionStatus: Option[String] = None,
  executionEvents: Option[List[ExecutionEvent]] = None,
  // status of the backend
  backendStatus: Option[String] = None,
  // map of log files from backend
  backendLogs: Option[Any] = None,
  // map from output names to local files
  outputs: Option[Any] = None,
  // starting time of call
  start: Option[Date] = None,
  // ending time of call
  end: Option[Date] = None,
  // unique ID of job
  jobId: Option[String] = None,
  // result code
  returnCode: Option[Integer] = None,
  // type of backend executing the call
  backend: Option[String] = None,
  // location of stdout
  stdout: Option[String] = None,
  // location of stderr
  stderr: Option[String] = None,
  // call index within a scatter block, as reported by execution service
  shardIndex: Option[Integer] = None
)

