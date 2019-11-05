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

@JsonCodec case class ConfigurationPayload (
  // Name of this config
  name: Option[String] = None,
  // Namespace of this config
  namespace: Option[String] = None,
  methodRepoMethod: Option[ConfigurationPayloadMethodRepoMethod] = None,
  // Map[String, AttributeString] from method's WDL outputs to fields in the workspace data model
  outputs: Option[Map[String, String]] = None,
  // Map[String, AttributeString] from method's WDL inputs to fields in the workspace data model
  inputs: Option[Map[String, String]] = None,
  rootEntityType: Option[String] = None,
  // Map[String, AttributeString]
  prerequisites: Option[Map[String, String]] = None,
  // Snapshot ID of this config
  methodConfigVersion: Option[Integer] = None,
  // Has this config been deleted?
  deleted: Option[Boolean] = None
)
