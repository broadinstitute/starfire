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


case class WorkspaceCatalogUpdateResponseList (
  // the users or groups who were updated
  usersUpdated: List[WorkspaceCatalogResponse],
  // the emails of users or groups who were not found
  emailsNotFound: List[String]
)

