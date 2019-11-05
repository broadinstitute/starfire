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

package org.broadinstitute.starfire.model

import io.swagger.sttp.utils.Decoders._
import io.swagger.sttp.utils.Encoders._
import io.circe.generic.JsonCodec

@JsonCodec case class WorkflowQueueStatusResponse (
  // estimated milliseconds until the current queue is submitted
  estimatedQueueTimeMS: Long,
  // the number of workflows in the queue ahead of the user's first workflow
  workflowsBeforeNextUserWorkflow: Integer,
  // Map[String,Int]
  workflowCountsByStatus: Map[String, Integer]
)
