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

@JsonCodec case class TerraPreference (
  // When true, prefer Terra UI; when false, prefer Legacy FireCloud UI.
  preferTerra: Option[Boolean] = None,
  // Epoch timestamp representing when the Terra Preference was last saved.
  preferTerraLastUpdated: Option[Long] = None
)

