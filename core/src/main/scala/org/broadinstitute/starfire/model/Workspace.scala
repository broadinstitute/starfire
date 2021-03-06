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

import java.util.Date
import java.util.Date._
import io.swagger.sttp.utils.Decoders._
import io.swagger.sttp.utils.Encoders._
import io.circe.generic.JsonCodec

@JsonCodec case class Workspace (
  // The namespace the workspace belongs to
  namespace: String,
  // The name of the workspace
  name: String,
  // The list of groups to form the Authorization Domain (empty if no Authorization Domain is set)
  authorizationDomain: List[ManagedGroupRef],
  // A UUID associated with the workspace
  workspaceId: String,
  // The name of the bucket associated with the workspace
  bucketName: String,
  // The date the workspace was created in yyyy-MM-ddTHH:mm:ss.SSSZZ format
  createdDate: Date,
  // The date the workspace was last modified in yyyy-MM-ddTHH:mm:ss.SSSZZ format
  lastModified: Date,
  // The user who created the workspace
  createdBy: String,
  // Map[String, Attribute]
  attributes: Map[String, String],
  // Map[String, RawlsGroupRef]
  accessLevels: Option[Map[String, RawlsGroupRef]] = None,
  // Map[String, RawlsGroupRef]
  authDomainACLs: Option[Map[String, RawlsGroupRef]] = None,
  // Can the Workspace currently be modified?
  isLocked: Boolean,
  workflowCollectionName: String
)

