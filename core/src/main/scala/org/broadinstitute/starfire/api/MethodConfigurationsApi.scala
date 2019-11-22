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

import org.broadinstitute.starfire.model.ConfigurationIngest
import org.broadinstitute.starfire.model.ConfigurationIngest._
import org.broadinstitute.starfire.model.CopyConfigurationIngest
import org.broadinstitute.starfire.model.CopyConfigurationIngest._
import org.broadinstitute.starfire.model.MethodConfigRename
import org.broadinstitute.starfire.model.MethodConfigRename._
import org.broadinstitute.starfire.model.NewMethodConfigIngest
import org.broadinstitute.starfire.model.NewMethodConfigIngest._
import org.broadinstitute.starfire.model.PermissionReport
import org.broadinstitute.starfire.model.PermissionReport._
import org.broadinstitute.starfire.model.PermissionReportRequest
import org.broadinstitute.starfire.model.PermissionReportRequest._
import org.broadinstitute.starfire.model.PublishConfigurationIngest
import org.broadinstitute.starfire.model.PublishConfigurationIngest._
import org.broadinstitute.starfire.model.UserImportPermission
import org.broadinstitute.starfire.model.UserImportPermission._
import org.broadinstitute.starfire.model.ValidatedMethodConfiguration
import org.broadinstitute.starfire.model.ValidatedMethodConfiguration._

import sttp.client._
import sttp.client.circe._
import io.circe.generic.auto._

import io.swagger.sttp.utils.Decoders._
import io.swagger.sttp.utils.Encoders._
import io.swagger.sttp.utils.SttpUtils.Implicits._

object MethodConfigurationsApi {

  /**
   * Copy a Method Repository Configuration into a workspace
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param configToCopy Method Configuration to Copy 
   * @return Option[io.circe.Json]
   */
  def copyFromMethodRepo(
    workspaceNamespace: String,
    workspaceName: String,
    configToCopy: CopyConfigurationIngest): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling MethodConfigurationsApi->copyFromMethodRepo")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling MethodConfigurationsApi->copyFromMethodRepo")
    assert(configToCopy != null, "Missing required parameter 'configToCopy' when calling MethodConfigurationsApi->copyFromMethodRepo")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/method_configs/copyFromMethodRepo")
      .body(configToCopy)
      .response(asJson[io.circe.Json])
  }

  /**
   * Copy a Method Config in a workspace to the Method Repository
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param configToCopy Method Configuration to Copy 
   * @return Option[io.circe.Json]
   */
  def copyToMethodRepo(
    workspaceNamespace: String,
    workspaceName: String,
    configToCopy: PublishConfigurationIngest): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling MethodConfigurationsApi->copyToMethodRepo")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling MethodConfigurationsApi->copyToMethodRepo")
    assert(configToCopy != null, "Missing required parameter 'configToCopy' when calling MethodConfigurationsApi->copyToMethodRepo")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/method_configs/copyToMethodRepo")
      .body(configToCopy)
      .response(asJson[io.circe.Json])
  }

  /**
   * Delete a method configuration in a workspace
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param configNamespace Configuration Namespace 
   * @param configName Configuration Name 
   * @return Option[io.circe.Json]
   */
  def deleteWorkspaceMethodConfig(
    workspaceNamespace: String,
    workspaceName: String,
    configNamespace: String,
    configName: String): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling MethodConfigurationsApi->deleteWorkspaceMethodConfig")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling MethodConfigurationsApi->deleteWorkspaceMethodConfig")
    assert(configNamespace != null, "Missing required parameter 'configNamespace' when calling MethodConfigurationsApi->deleteWorkspaceMethodConfig")
    assert(configName != null, "Missing required parameter 'configName' when calling MethodConfigurationsApi->deleteWorkspaceMethodConfig")

    basicRequest
      .delete(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/method_configs/${configNamespace}/${configName}")
      .response(asJson[io.circe.Json])
  }

  /**
   * Get a method configuration in a workspace
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param configNamespace Configuration Namespace 
   * @param configName Configuration Name 
   * @return Option[io.circe.Json]
   */
  def getWorkspaceMethodConfig(
    workspaceNamespace: String,
    workspaceName: String,
    configNamespace: String,
    configName: String): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling MethodConfigurationsApi->getWorkspaceMethodConfig")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling MethodConfigurationsApi->getWorkspaceMethodConfig")
    assert(configNamespace != null, "Missing required parameter 'configNamespace' when calling MethodConfigurationsApi->getWorkspaceMethodConfig")
    assert(configName != null, "Missing required parameter 'configName' when calling MethodConfigurationsApi->getWorkspaceMethodConfig")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/method_configs/${configNamespace}/${configName}")
      .response(asJson[io.circe.Json])
  }

  /**
   * Check the user&#39;s ability to import a method config into a workspace
   * 
   *
   * @return Option[UserImportPermission]
   */
  def importStatus(
    ): Request[Either[ResponseError[io.circe.Error],UserImportPermission],Nothing] = {

    basicRequest
      .get(uri"https://api.firecloud.org/api/profile/importstatus")
      .response(asJson[UserImportPermission])
  }

  /**
   * List method configurations in a workspace
   * List method configurations in a workspace. By default, only Agora method configs are returned. To return configs for all repos, specify &#x60;allRepos&#x60; to be &#x60;true&#x60;.  #### Expanded discussion on the methodRepoMethod field  This endpoint returns method references to Agora in the format &#x60;&#x60;&#x60; \&quot;methodRepoMethod\&quot;: {   \&quot;methodNamespace\&quot;: \&quot;namespace\&quot;,   \&quot;methodName\&quot;: \&quot;name\&quot;,   \&quot;methodVersion\&quot;: 1,   \&quot;sourceRepo\&quot;: \&quot;agora\&quot;,   \&quot;methodUri\&quot;: \&quot;agora://namespace/name/1\&quot; } &#x60;&#x60;&#x60; and for Dockstore in the format &#x60;&#x60;&#x60; \&quot;methodRepoMethod\&quot;: {   \&quot;methodPath\&quot;: \&quot;path\&quot;,   \&quot;methodVersion\&quot;: \&quot;version\&quot;,   \&quot;sourceRepo\&quot;: \&quot;agora\&quot;,   \&quot;methodUri\&quot;: \&quot;dockstore://path/version\&quot; } &#x60;&#x60;&#x60; If you are only working with Agora methods, the fields &#x60;\&quot;sourceRepo\&quot;&#x60; and &#x60;\&quot;methodUri\&quot;&#x60; can be considered informational and do not need to be round-tripped (see the corresponding &#x60;POST /api/workspaces/{workspaceNamespace}/{workspaceName}/methodconfigs&#x60; for more details). 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param allRepos Configs for all repos, not just Agora (optional, default to false)
   * @return Option[io.circe.Json]
   */
  def listWorkspaceMethodConfigs(
    workspaceNamespace: String,
    workspaceName: String,
    allRepos: Option[Boolean] = Option(false)
    ): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling MethodConfigurationsApi->listWorkspaceMethodConfigs")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling MethodConfigurationsApi->listWorkspaceMethodConfigs")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/methodconfigs?allRepos=${allRepos}")
      .response(asJson[io.circe.Json])
  }

  /**
   * Add or overwrite a method configuration in a workspace
   * Add or overwrite a method configuration in a workspace. The method configuration name and namespace in the URI must match the values in the JSON. 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param configNamespace Configuration Namespace 
   * @param configName Configuration Name 
   * @param body Method Config to Update 
   * @return Option[io.circe.Json]
   */
  def overwriteWorkspaceMethodConfig(
    workspaceNamespace: String,
    workspaceName: String,
    configNamespace: String,
    configName: String,
    body: ConfigurationIngest): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling MethodConfigurationsApi->overwriteWorkspaceMethodConfig")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling MethodConfigurationsApi->overwriteWorkspaceMethodConfig")
    assert(configNamespace != null, "Missing required parameter 'configNamespace' when calling MethodConfigurationsApi->overwriteWorkspaceMethodConfig")
    assert(configName != null, "Missing required parameter 'configName' when calling MethodConfigurationsApi->overwriteWorkspaceMethodConfig")
    assert(body != null, "Missing required parameter 'body' when calling MethodConfigurationsApi->overwriteWorkspaceMethodConfig")

    basicRequest
      .put(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/method_configs/${configNamespace}/${configName}")
      .body(body)
      .response(asJson[io.circe.Json])
  }

  /**
   * Create a Method Configuration in a workspace
   * Create method configurations. #### Expanded discussion on the methodRepoMethod field To create a method config for an Agora method, &#x60;\&quot;sourceRepo\&quot;: \&quot;agora\&quot;&#x60; is optional; the following is sufficient: &#x60;&#x60;&#x60; \&quot;methodRepoMethod\&quot;: {   \&quot;methodNamespace\&quot;: \&quot;namespace\&quot;,   \&quot;methodName\&quot;: \&quot;name\&quot;,   \&quot;methodVersion\&quot;: 1 } &#x60;&#x60;&#x60; To create a method config for a Dockstore method, &#x60;\&quot;sourceRepo\&quot;: \&quot;dockstore\&quot;&#x60; is required: &#x60;&#x60;&#x60; \&quot;methodRepoMethod\&quot;: {   \&quot;sourceRepo\&quot;: \&quot;dockstore\&quot;,   \&quot;methodPath\&quot;: \&quot;path\&quot;,   \&quot;methodVersion\&quot;: \&quot;version\&quot; } &#x60;&#x60;&#x60; You may also use the URI on its own to create a config referencing any supported repo (currently Agora and Dockstore): &#x60;&#x60;&#x60; \&quot;methodRepoMethod\&quot;: {   \&quot;methodUri\&quot;: \&quot;agora://namespace/name/1\&quot; } &#x60;&#x60;&#x60; &#x60;&#x60;&#x60; \&quot;methodRepoMethod\&quot;: {   \&quot;methodUri\&quot;: \&quot;dockstore://path/version\&quot; } &#x60;&#x60;&#x60; The system is specified to check for a URI first before falling back to the legacy fields. Unsupported repos will return a 400 Bad Request. 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param methodConfigJson Method Configuration contents 
   * @return Option[io.circe.Json]
   */
  def postWorkspaceMethodConfig(
    workspaceNamespace: String,
    workspaceName: String,
    methodConfigJson: NewMethodConfigIngest): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling MethodConfigurationsApi->postWorkspaceMethodConfig")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling MethodConfigurationsApi->postWorkspaceMethodConfig")
    assert(methodConfigJson != null, "Missing required parameter 'methodConfigJson' when calling MethodConfigurationsApi->postWorkspaceMethodConfig")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/methodconfigs")
      .body(methodConfigJson)
      .response(asJson[io.circe.Json])
  }

  /**
   * Rename a method configuration in a workspace
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param configNamespace Configuration Namespace 
   * @param configName Configuration Name 
   * @param rename Method Config Rename 
   * @return Option[io.circe.Json]
   */
  def renameWorkspaceMethodConfig(
    workspaceNamespace: String,
    workspaceName: String,
    configNamespace: String,
    configName: String,
    rename: MethodConfigRename): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling MethodConfigurationsApi->renameWorkspaceMethodConfig")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling MethodConfigurationsApi->renameWorkspaceMethodConfig")
    assert(configNamespace != null, "Missing required parameter 'configNamespace' when calling MethodConfigurationsApi->renameWorkspaceMethodConfig")
    assert(configName != null, "Missing required parameter 'configName' when calling MethodConfigurationsApi->renameWorkspaceMethodConfig")
    assert(rename != null, "Missing required parameter 'rename' when calling MethodConfigurationsApi->renameWorkspaceMethodConfig")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/method_configs/${configNamespace}/${configName}/rename")
      .body(rename)
      .response(asJson[io.circe.Json])
  }

  /**
   * Update a method configuration in a workspace
   * Update method configuration. Updates and moves the method configuration at the URI to the location in the request body. The location in the URI may be the same as the location in the request body. If the location in the request body matches the location in the URI, it is overwritten. If the location in the request body is different to the location in the URI, and there is a method config already at that location, 409 is returned. 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param configNamespace Configuration Namespace 
   * @param configName Configuration Name 
   * @param body Method Config to Update 
   * @return Option[io.circe.Json]
   */
  def updateWorkspaceMethodConfig(
    workspaceNamespace: String,
    workspaceName: String,
    configNamespace: String,
    configName: String,
    body: ConfigurationIngest): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling MethodConfigurationsApi->updateWorkspaceMethodConfig")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling MethodConfigurationsApi->updateWorkspaceMethodConfig")
    assert(configNamespace != null, "Missing required parameter 'configNamespace' when calling MethodConfigurationsApi->updateWorkspaceMethodConfig")
    assert(configName != null, "Missing required parameter 'configName' when calling MethodConfigurationsApi->updateWorkspaceMethodConfig")
    assert(body != null, "Missing required parameter 'body' when calling MethodConfigurationsApi->updateWorkspaceMethodConfig")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/method_configs/${configNamespace}/${configName}")
      .body(body)
      .response(asJson[io.circe.Json])
  }

  /**
   * get syntax validation information for a method configuration
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param configNamespace Method Configuration Namespace 
   * @param configName Method Configuration Name 
   * @return Option[ValidatedMethodConfiguration]
   */
  def validateMethodConfiguration(
    workspaceNamespace: String,
    workspaceName: String,
    configNamespace: String,
    configName: String): Request[Either[ResponseError[io.circe.Error],ValidatedMethodConfiguration],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling MethodConfigurationsApi->validateMethodConfiguration")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling MethodConfigurationsApi->validateMethodConfiguration")
    assert(configNamespace != null, "Missing required parameter 'configNamespace' when calling MethodConfigurationsApi->validateMethodConfiguration")
    assert(configName != null, "Missing required parameter 'configName' when calling MethodConfigurationsApi->validateMethodConfiguration")

    basicRequest
      .get(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/method_configs/${configNamespace}/${configName}/validate")
      .response(asJson[ValidatedMethodConfiguration])
  }

  /**
   * Retrieve user permissions for the workspace and the workspace&#39;s method references
   * 
   *
   * @param workspaceNamespace Workspace Namespace 
   * @param workspaceName Workspace Name 
   * @param reportInput Users and/or configs on which to report, both optional 
   * @return Option[PermissionReport]
   */
  def workspacePermissionReport(
    workspaceNamespace: String,
    workspaceName: String,
    reportInput: PermissionReportRequest): Request[Either[ResponseError[io.circe.Error],PermissionReport],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling MethodConfigurationsApi->workspacePermissionReport")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling MethodConfigurationsApi->workspacePermissionReport")
    assert(reportInput != null, "Missing required parameter 'reportInput' when calling MethodConfigurationsApi->workspacePermissionReport")

    basicRequest
      .post(uri"https://api.firecloud.org/api/workspaces/${workspaceNamespace}/${workspaceName}/permissionReport")
      .body(reportInput)
      .response(asJson[PermissionReport])
  }

}

