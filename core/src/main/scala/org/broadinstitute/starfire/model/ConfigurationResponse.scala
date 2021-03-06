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

import io.circe.Json
import io.circe.Json._
import io.swagger.sttp.utils.Decoders._
import io.swagger.sttp.utils.Encoders._
import io.circe.generic.JsonCodec

@JsonCodec case class ConfigurationResponse (
  // List of Owners.
  managers: Option[List[String]] = None,
  method: Option[Map[String, Json]] = None,
  // Namespace which contains AgoraEntity.
  namespace: Option[String] = None,
  // Name of the AgoraEntity.
  name: Option[String] = None,
  // SnapshotId of AgoraEntity
  snapshotId: Option[Integer] = None,
  // Snapshot comment of AgoraEntity
  snapshotComment: Option[String] = None,
  // Synopsis which contains AgoraEntity.
  synopsis: Option[String] = None,
  // Documentation of the AgoraEntity. MUST BE REQUESTED EXPLICITLY.
  documentation: Option[String] = None,
  // Timestamp of creation
  createDate: Option[String] = None,
  // URL where resource can be accessed.
  url: Option[String] = None,
  // Payload of config - JSON format in a string
  payload: Option[String] = None,
  // Type of the AgoraEntity -- Task or Workflow.
  entityType: Option[String] = None,
  // Is this method publicly readable?
  public: Option[Boolean] = None
)

