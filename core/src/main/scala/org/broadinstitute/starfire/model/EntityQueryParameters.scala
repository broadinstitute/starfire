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

@JsonCodec case class EntityQueryParameters (
  // page number, 1-indexed positive integer
  page: Integer,
  // count of items per page, positive integer
  pageSize: Integer,
  // field to sort by
  sortField: String,
  // asc or desc
  sortDirection: String,
  // terms to filter results by
  filterTerms: Option[String] = None
)
