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

import org.broadinstitute.starfire.model.BillingProjectMember
import org.broadinstitute.starfire.model.BillingProjectMember._
import org.broadinstitute.starfire.model.CreateRawlsBillingProjectFullRequest
import org.broadinstitute.starfire.model.CreateRawlsBillingProjectFullRequest._
import org.broadinstitute.starfire.model.ErrorReport
import org.broadinstitute.starfire.model.ErrorReport._

import sttp.client._
import sttp.client.circe._
import io.circe.generic.auto._

import io.swagger.sttp.utils.Decoders._
import io.swagger.sttp.utils.Encoders._
import io.swagger.sttp.utils.SttpUtils.Implicits._

object BillingApi {

  /**
   * add user to billing project the caller owns
   * 
   *
   * @param projectId Project ID 
   * @param workbenchRole role of user for project 
   * @param email email of user or group to add 
   * @return Option[io.circe.Json]
   */
  def addUserToBillingProject(
    projectId: String,
    workbenchRole: String,
    email: String): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(projectId != null, "Missing required parameter 'projectId' when calling BillingApi->addUserToBillingProject")
    assert(workbenchRole != null, "Missing required parameter 'workbenchRole' when calling BillingApi->addUserToBillingProject")
    assert(email != null, "Missing required parameter 'email' when calling BillingApi->addUserToBillingProject")

    basicRequest
      .put(uri"https://api.firecloud.org/api/billing/${projectId}/${workbenchRole}/${email}")
      .response(asJson[io.circe.Json])
  }

  /**
   * create billing project in FireCloud and google
   * 
   *
   * @param createProjectRequest create project request 
   * @return Option[io.circe.Json]
   */
  def createBillingProjectFull(
    createProjectRequest: CreateRawlsBillingProjectFullRequest): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(createProjectRequest != null, "Missing required parameter 'createProjectRequest' when calling BillingApi->createBillingProjectFull")

    basicRequest
      .post(uri"https://api.firecloud.org/api/billing")
      .body(createProjectRequest)
      .response(asJson[io.circe.Json])
  }

  /**
   * grant a google role to a user and their pet in the billing project the caller owns
   * 
   *
   * @param projectId Project ID 
   * @param role google role of user for project 
   * @param email email of user 
   * @return Option[io.circe.Json]
   */
  def grantGoogleRoleToUser(
    projectId: String,
    role: String,
    email: String): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(projectId != null, "Missing required parameter 'projectId' when calling BillingApi->grantGoogleRoleToUser")
    assert(role != null, "Missing required parameter 'role' when calling BillingApi->grantGoogleRoleToUser")
    assert(email != null, "Missing required parameter 'email' when calling BillingApi->grantGoogleRoleToUser")

    basicRequest
      .put(uri"https://api.firecloud.org/api/billing/${projectId}/googleRole/${role}/${email}")
      .response(asJson[io.circe.Json])
  }

  /**
   * list members of billing project the caller owns
   * 
   *
   * @param projectId Project ID 
   * @return Option[List[BillingProjectMember]]
   */
  def listBillingProjectMembers(
    projectId: String): Request[Either[ResponseError[io.circe.Error],List[BillingProjectMember]],Nothing] = {
    assert(projectId != null, "Missing required parameter 'projectId' when calling BillingApi->listBillingProjectMembers")

    basicRequest
      .get(uri"https://api.firecloud.org/api/billing/${projectId}/members")
      .response(asJson[List[BillingProjectMember]])
  }

  /**
   * remove a google role from a user and their pet in the billing project the caller owns
   * 
   *
   * @param projectId Project ID 
   * @param role google role of user for project 
   * @param email email of user 
   * @return Option[io.circe.Json]
   */
  def removeGoogleRoleFromUser(
    projectId: String,
    role: String,
    email: String): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(projectId != null, "Missing required parameter 'projectId' when calling BillingApi->removeGoogleRoleFromUser")
    assert(role != null, "Missing required parameter 'role' when calling BillingApi->removeGoogleRoleFromUser")
    assert(email != null, "Missing required parameter 'email' when calling BillingApi->removeGoogleRoleFromUser")

    basicRequest
      .delete(uri"https://api.firecloud.org/api/billing/${projectId}/googleRole/${role}/${email}")
      .response(asJson[io.circe.Json])
  }

  /**
   * remove user from billing project the caller owns
   * 
   *
   * @param projectId Project ID 
   * @param workbenchRole role of user for project 
   * @param email email of user or group to remove 
   * @return Option[io.circe.Json]
   */
  def removeUserFromBillingProject(
    projectId: String,
    workbenchRole: String,
    email: String): Request[Either[ResponseError[io.circe.Error],io.circe.Json],Nothing] = {
    assert(projectId != null, "Missing required parameter 'projectId' when calling BillingApi->removeUserFromBillingProject")
    assert(workbenchRole != null, "Missing required parameter 'workbenchRole' when calling BillingApi->removeUserFromBillingProject")
    assert(email != null, "Missing required parameter 'email' when calling BillingApi->removeUserFromBillingProject")

    basicRequest
      .delete(uri"https://api.firecloud.org/api/billing/${projectId}/${workbenchRole}/${email}")
      .response(asJson[io.circe.Json])
  }

}

