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

import org.broadinstitute.starfire.model.NotificationType
import org.broadinstitute.starfire.model.NotificationType._

import sttp.client._
import sttp.client.circe._
import io.circe.generic.auto._

import io.swagger.client.Decoders._
import io.swagger.client.Encoders._
import io.swagger.client.SttpUtils.Implicits._

class NotificationsApi() {

  /**
   * Gets the general notifications available
   * 
   *
   * @return Option[List[NotificationType]]
   */
  def generalNotifications(
    ): Request[Either[ResponseError[io.circe.Error],List[NotificationType]],Nothing] = {

    basicRequest
      .get(uri"https://localhost/api/notifications/general")
      .response(asJson[List[NotificationType]])
  }

  /**
   * Gets the notifications available for a workspace
   * 
   *
   * @param workspaceNamespace workspace namespace 
   * @param workspaceName workspace name 
   * @return Option[List[NotificationType]]
   */
  def workspaceNotifications(
    workspaceNamespace: String,
    workspaceName: String): Request[Either[ResponseError[io.circe.Error],List[NotificationType]],Nothing] = {
    assert(workspaceNamespace != null, "Missing required parameter 'workspaceNamespace' when calling NotificationsApi->workspaceNotifications")
    assert(workspaceName != null, "Missing required parameter 'workspaceName' when calling NotificationsApi->workspaceNotifications")

    basicRequest
      .get(uri"https://localhost/api/notifications/workspace/${workspaceNamespace}/${workspaceName}")
      .response(asJson[List[NotificationType]])
  }

}

