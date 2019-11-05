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

@JsonCodec case class MethodQuery (
  // Namespace which contains AgoraEntity.
  namespace: Option[String] = None,
  // Name of the AgoraEntity.
  name: Option[String] = None,
  // Synopsis which contains AgoraEntity.
  synopsis: Option[String] = None,
  // Snapshot comment of AgoraEntity
  snapshotComment: Option[String] = None,
  // Documentation of the AgoraEntity.
  documentation: Option[String] = None,
  // Payload of method -- must be in WDL format
  payload: Option[String] = None,
  // Type of the AgoraEntity -- Task or Workflow.
  entityType: Option[String] = None
)

