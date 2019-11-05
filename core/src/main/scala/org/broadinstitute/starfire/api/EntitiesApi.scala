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

package org.broadinstitute.starfire.api

import org.broadinstitute.starfire.model.AttributeUpdateOperation
import org.broadinstitute.starfire.model.AttributeUpdateOperation._
import org.broadinstitute.starfire.model.BagitRequest
import org.broadinstitute.starfire.model.BagitRequest._
import org.broadinstitute.starfire.model.CopyEntity
import org.broadinstitute.starfire.model.CopyEntity._
import org.broadinstitute.starfire.model.Entity
import org.broadinstitute.starfire.model.Entity._
import org.broadinstitute.starfire.model.EntityCopyResponse
import org.broadinstitute.starfire.model.EntityCopyResponse._
import org.broadinstitute.starfire.model.EntityID
import org.broadinstitute.starfire.model.EntityID._
import org.broadinstitute.starfire.model.EntityQueryResponse
import org.broadinstitute.starfire.model.EntityQueryResponse._
import org.broadinstitute.starfire.model.ErrorReport
import org.broadinstitute.starfire.model.ErrorReport._
import better.files.File
import better.files.File._
import org.broadinstitute.starfire.model.PFBRequest
import org.broadinstitute.starfire.model.PFBRequest._

import sttp.client._
import sttp.client.circe._
import io.circe.generic.auto._

import io.swagger.sttp.utils.Decoders._
import io.swagger.sttp.utils.Encoders._
import io.swagger.sttp.utils.SttpUtils.Implicits._

object EntitiesApi {

  /**
   * TSV file containing workspace entities of the specified type 
   * swagger-ui seems to not handle file downloads, so this endpoint won&#39;t function through the ui. It is here for documentation purposes only. 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param entityType Entity Type 
   * @param fCtoken valid access token to use for authentication 
   * @param attributeNames comma separated list of ordered attribute names to be in downloaded tsv (optional)
   * @param model flexible or firecloud (firecloud model will be used by default) (optional)
   * @return Option[File]
   */
  def browserDownloadEntitiesTSV(
    workspaceNamespace: String,
    workspaceName: String,
    entityType: String,
    fCtoken: String,
    attributeNames: Option[String] = None,
    model: Option[String] = None
    ): Request[Either[ResponseError[io.circe.Error],File],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->browserDownloadEntitiesTSV")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->browserDownloadEntitiesTSV")
    assert(entityType != null, "Missing required parameter 'entityType' when calling EntitiesApi->browserDownloadEntitiesTSV")
    assert(fCtoken != null, "Missing required parameter 'fCtoken' when calling EntitiesApi->browserDownloadEntitiesTSV")

    basicRequest
      .post(uri"https://api.firecloud.org/cookie-authed/workspaces/${workspaceNamespace}/${workspaceName}/entities/${entityType}/tsv")
      .response(asJson[File])
  }

  /**
   * TSV file containing workspace entities of the specified type 
   * swagger-ui seems to not handle file downloads, so this endpoint won&#39;t function through the ui. It is here for documentation purposes only. 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param entityType Entity Type 
   * @param attributeNames comma separated list of ordered attribute names to be in downloaded tsv (optional)
   * @param model flexible or firecloud (firecloud model will be used by default) (optional)
   * @return Option[File]
   */
  def browserDownloadEntitiesTSVGet(
    workspaceNamespace: String,
    workspaceName: String,
    entityType: String,
    attributeNames: Option[String] = None,
    model: Option[String] = None
    ): Request[Either[ResponseError[io.circe.Error],File],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->browserDownloadEntitiesTSVGet")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->browserDownloadEntitiesTSVGet")
    assert(entityType != null, "Missing required parameter 'entityType' when calling EntitiesApi->browserDownloadEntitiesTSVGet")

    basicRequest
      .get(uri"https://api.firecloud.org/cookie-authed/workspaces/${workspaceNamespace}/${workspaceName}/entities/${entityType}/tsv?attributeNames=${attributeNames}?model=${model}")
      .response(asJson[File])
  }

  /**
   * Copy entities from one workspace to another 
   * 
   *
   * @param workspaceNamespace Destination Workspace Namespace 
   * @param workspaceName Destination Workspace Name 
   * @param body Entities to Copy 
   * @param linkExistingEntities true to link new entities to existing entities, false to fail instead of link (optional, default to false)
   * @return Option[EntityCopyResponse]
   */
  def copyEntities(
    workspaceNamespace: String,
    workspaceName: String,
    body: CopyEntity,
    linkExistingEntities: Option[Boolean] = Option(false)
    ): Request[Either[ResponseError[io.circe.Error],EntityCopyResponse],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->copyEntities")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->copyEntities")
    assert(body != null, "Missing required parameter 'body' when calling EntitiesApi->copyEntities")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/entities/copy?linkExistingEntities=${linkExistingEntities}")
      .body(body)
      .response(asJson[EntityCopyResponse])
  }

  /**
   * Bulk delete entities from a workspace
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param body Entities to delete 
   * @return Option[Unit]
   */
  def deleteEntities(
    workspaceNamespace: String,
    workspaceName: String,
    body: List[EntityID]): Request[Either[ResponseError[io.circe.Error],Unit],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->deleteEntities")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->deleteEntities")
    assert(body != null, "Missing required parameter 'body' when calling EntitiesApi->deleteEntities")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/entities/delete")
      .body(body)
      .response(asJson[Unit])
  }

  /**
   * TSV file containing workspace entities of the specified type 
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param entityType Entity Type 
   * @param attributeNames comma separated list of ordered attribute names to be in downloaded tsv (optional)
   * @param model firecloud (default) or flexible (optional)
   * @return Option[File]
   */
  def downloadEntitiesTSV(
    workspaceNamespace: String,
    workspaceName: String,
    entityType: String,
    attributeNames: Option[String] = None,
    model: Option[String] = None
    ): Request[Either[ResponseError[io.circe.Error],File],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->downloadEntitiesTSV")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->downloadEntitiesTSV")
    assert(entityType != null, "Missing required parameter 'entityType' when calling EntitiesApi->downloadEntitiesTSV")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/entities/${entityType}/tsv?attributeNames=${attributeNames}?model=${model}")
      .response(asJson[File])
  }

  /**
   * Paginated query for entities in a workspace
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param entityType Entity Type 
   * @param page Page number, 1-indexed (default 1) (optional, default to 1)
   * @param pageSize Page size (default 10) (optional, default to 10)
   * @param sortField Sort field (default \&quot;name\&quot;) (optional, default to name)
   * @param sortDirection Sort direction (asc or desc, default asc) (optional, default to asc)
   * @param filterTerms Filter terms (optional)
   * @return Option[EntityQueryResponse]
   */
  def entityQuery(
    workspaceNamespace: String,
    workspaceName: String,
    entityType: String,
    page: Option[Integer] = Option(1),
    pageSize: Option[Integer] = Option(10),
    sortField: Option[String] = Option("name"),
    sortDirection: Option[String] = Option("asc"),
    filterTerms: Option[String] = None
    ): Request[Either[ResponseError[io.circe.Error],EntityQueryResponse],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->entityQuery")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->entityQuery")
    assert(entityType != null, "Missing required parameter 'entityType' when calling EntitiesApi->entityQuery")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/entityQuery/${entityType}?page=${page}?pageSize=${pageSize}?sortField=${sortField}?sortDirection=${sortDirection}?filterTerms=${filterTerms}")
      .response(asJson[EntityQueryResponse])
  }

  /**
   * Evaluate entity expression
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param entityType Entity Type 
   * @param entityName Entity Name 
   * @param expression Expression 
   * @return Option[Unit]
   */
  def evaluateEntityExpression(
    workspaceNamespace: String,
    workspaceName: String,
    entityType: String,
    entityName: String,
    expression: String): Request[Either[ResponseError[io.circe.Error],Unit],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->evaluateEntityExpression")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->evaluateEntityExpression")
    assert(entityType != null, "Missing required parameter 'entityType' when calling EntitiesApi->evaluateEntityExpression")
    assert(entityName != null, "Missing required parameter 'entityName' when calling EntitiesApi->evaluateEntityExpression")
    assert(expression != null, "Missing required parameter 'expression' when calling EntitiesApi->evaluateEntityExpression")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/entities/${entityType}/${entityName}/evaluate")
      .body(expression)
      .response(asJson[Unit])
  }

  /**
   * Import entities from a tsv file
   * 
   *
   * @param workspaceNamespace Destination Workspace Namespace 
   * @param workspaceName Destination Workspace Name 
   * @param entities A valid TSV import file 
   * @return Option[Unit]
   */
  def flexibleImportEntities(
    workspaceNamespace: String,
    workspaceName: String,
    entities: File): Request[Either[ResponseError[io.circe.Error],Unit],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->flexibleImportEntities")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->flexibleImportEntities")
    assert(entities != null, "Missing required parameter 'entities' when calling EntitiesApi->flexibleImportEntities")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/flexibleImportEntities")
      .response(asJson[Unit])
  }

  /**
   * List of entities in a workspace 
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param entityType Entity Type 
   * @return Option[List[Entity]]
   */
  def getEntities(
    workspaceNamespace: String,
    workspaceName: String,
    entityType: String): Request[Either[ResponseError[io.circe.Error],List[Entity]],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->getEntities")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->getEntities")
    assert(entityType != null, "Missing required parameter 'entityType' when calling EntitiesApi->getEntities")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/entities/${entityType}")
      .response(asJson[List[Entity]])
  }

  /**
   * List of entities in a workspace with type and attribute information 
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @return Option[Unit]
   */
  def getEntitiesWithType(
    workspaceNamespace: String,
    workspaceName: String): Request[Either[ResponseError[io.circe.Error],Unit],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->getEntitiesWithType")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->getEntitiesWithType")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/entities_with_type")
      .response(asJson[Unit])
  }

  /**
   * Get entity in a workspace
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param entityType Entity Type 
   * @param entityName Entity Name 
   * @return Option[Unit]
   */
  def getEntity(
    workspaceNamespace: String,
    workspaceName: String,
    entityType: String,
    entityName: String): Request[Either[ResponseError[io.circe.Error],Unit],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->getEntity")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->getEntity")
    assert(entityType != null, "Missing required parameter 'entityType' when calling EntitiesApi->getEntity")
    assert(entityName != null, "Missing required parameter 'entityName' when calling EntitiesApi->getEntity")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/entities/${entityType}/${entityName}")
      .response(asJson[Unit])
  }

  /**
   * List of entity types in a workspace 
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @return Option[Unit]
   */
  def getEntityTypes(
    workspaceNamespace: String,
    workspaceName: String): Request[Either[ResponseError[io.circe.Error],Unit],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->getEntityTypes")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->getEntityTypes")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/entities")
      .response(asJson[Unit])
  }

  /**
   * Import entity TSVs from a zipped [BagIt](https://tools.ietf.org/html/draft-kunze-bagit-14) directory, whose payload contains two files - participants.tsv and samples.tsv
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param bagitImportRequest JSON object containing bagit URL 
   * @return Option[Unit]
   */
  def importBagit(
    workspaceNamespace: String,
    workspaceName: String,
    bagitImportRequest: BagitRequest): Request[Either[ResponseError[io.circe.Error],Unit],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->importBagit")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->importBagit")
    assert(bagitImportRequest != null, "Missing required parameter 'bagitImportRequest' when calling EntitiesApi->importBagit")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/importBagit")
      .body(bagitImportRequest)
      .response(asJson[Unit])
  }

  /**
   * Import entities from a tsv file
   * 
   *
   * @param workspaceNamespace Destination Workspace Namespace 
   * @param workspaceName Destination Workspace Name 
   * @param entities A valid TSV import file 
   * @return Option[Unit]
   */
  def importEntities(
    workspaceNamespace: String,
    workspaceName: String,
    entities: File): Request[Either[ResponseError[io.circe.Error],Unit],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->importEntities")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->importEntities")
    assert(entities != null, "Missing required parameter 'entities' when calling EntitiesApi->importEntities")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/importEntities")
      .response(asJson[Unit])
  }

  /**
   * Import PFB data from an [Avro](https://avro.apache.org/) file
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param pfbImportRequest JSON object containing PFB URL 
   * @return Option[Unit]
   */
  def importPFB(
    workspaceNamespace: String,
    workspaceName: String,
    pfbImportRequest: PFBRequest): Request[Either[ResponseError[io.circe.Error],Unit],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->importPFB")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->importPFB")
    assert(pfbImportRequest != null, "Missing required parameter 'pfbImportRequest' when calling EntitiesApi->importPFB")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/importPFB")
      .body(pfbImportRequest)
      .response(asJson[Unit])
  }

  /**
   * Update entity in a workspace
   * Update an entity
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param entityType Entity Type 
   * @param entityName Entity Name 
   * @param attributeUpdateJson Update operations for attributes (optional)
   * @return Option[Entity]
   */
  def updateEntity(
    workspaceNamespace: String,
    workspaceName: String,
    entityType: String,
    entityName: String,
    attributeUpdateJson: Option[AttributeUpdateOperation] = None
    ): Request[Either[ResponseError[io.circe.Error],Entity],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling EntitiesApi->updateEntity")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling EntitiesApi->updateEntity")
    assert(entityType != null, "Missing required parameter 'entityType' when calling EntitiesApi->updateEntity")
    assert(entityName != null, "Missing required parameter 'entityName' when calling EntitiesApi->updateEntity")

    basicRequest
      .patch(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/entities/${entityType}/${entityName}")
      .body(attributeUpdateJson)
      .response(asJson[Entity])
  }

}
