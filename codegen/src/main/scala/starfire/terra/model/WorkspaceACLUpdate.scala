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


case class WorkspaceACLUpdate (
  // email address of the user or group whose permissions will be changed
  email: String,
  // The access level to grant to this user or group (OWNER, READER, WRITER, NO ACCESS)
  accessLevel: String,
  // Set to true if you want this user to be able to share the workspace with other users, only meaningful for readers and writers, default false
  canShare: Option[Boolean] = None,
  // Set to true if you want this user to be able to launch compute in this workspace, may not be true for readers, default false for reader, true otherwise
  canCompute: Option[Boolean] = None
)

