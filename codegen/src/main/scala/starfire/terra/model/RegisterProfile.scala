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


case class RegisterProfile (
  // The user's subject id
  userId: Option[String] = None,
  keyValuePairs: Option[List[KeyValuePair]] = None
)

