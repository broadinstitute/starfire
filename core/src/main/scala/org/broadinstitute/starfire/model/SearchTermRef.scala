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

@JsonCodec case class SearchTermRef (
  // The term to search for
  searchString: Option[String] = None,
  // Map[String, Array[String]] Each entry contains the column name (i.e. \"library:indication\") and a list of terms that will be or'ed in the query ([\"cancer\", \"sleep apnea\"])
  filters: Option[Map[String, List[String]]] = None,
  researchPurpose: Option[ResearchPurpose] = None,
  // Map[String, Int] The list of fields for which you would like to retrieve aggregations and the number of aggregations to return. Default is 5. Specify 0 to get all
  fieldAggregations: Option[Map[String, Integer]] = None,
  // Where in the results list to start (used for pagination)
  from: Option[Integer] = None,
  // How many results to return
  size: Option[Integer] = None,
  // Field name on which to sort; defaults to sort by relevance if not specified
  sortField: Option[String] = None,
  // asc or desc; defaults to asc if not specified
  sortDirection: Option[String] = None
)

