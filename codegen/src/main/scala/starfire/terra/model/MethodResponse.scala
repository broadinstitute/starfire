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


case class MethodResponse (
  // List of Owners.
  managers: List[String],
  // Namespace which contains AgoraEntity.
  namespace: String,
  // Name of the AgoraEntity.
  name: String,
  // SnapshotId of AgoraEntity
  snapshotId: Integer,
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
  // Payload of method -- must be in WDL format. MUST BE REQUESTED EXPLICITLY.
  payload: Option[String] = None,
  // Type of the AgoraEntity -- Task or Workflow.
  entityType: Option[String] = None
)

