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

@JsonCodec case class MethodDefinition (
  // Namespace which contains the method.
  namespace: Option[String] = None,
  // Name of the method.
  name: Option[String] = None,
  // Synopsis of the method
  synopsis: Option[String] = None,
  // List of Owners.
  managers: Option[List[String]] = None,
  // Is this method publicly readable?
  public: Option[Boolean] = None,
  // count of configurations associated with this method
  numConfigurations: Option[Integer] = None,
  // count of snapshots of this method
  numSnapshots: Option[Integer] = None,
  // always \"Workflow\".
  entityType: Option[String] = None
)

