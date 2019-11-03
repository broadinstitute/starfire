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

import org.broadinstitute.starfire.model.StringArray
import org.broadinstitute.starfire.model.StringArray._
import org.broadinstitute.starfire.model.TrialOperationResponse
import org.broadinstitute.starfire.model.TrialOperationResponse._

import sttp.client._
import sttp.client.circe._
import io.circe.generic.auto._

import io.swagger.client.Decoders._
import io.swagger.client.Encoders._
import io.swagger.client.SttpUtils.Implicits._

class TrialFireCloudCreditsApi() {

  /**
   * Manage projects in the FireCloud Free Credits Program; for managers of the Program only.
   * 
   *
   * @param operation operation to perform on projects. \&quot;Create\&quot; will create projects and verify those projects. \&quot;Verify\&quot; will verify all unverified projects in the pool. \&quot;Count\&quot; will return the number of projects in various statuses. \&quot;Adopt\&quot; will enter a previously-created project into the pool without verifying it. \&quot;Scratch\&quot; will mark a pool project as unavailable/errored and disassociate it from any user that claimed it. \&quot;Report\&quot; will return a report of all claimed projects.  
   * @param count number of projects to create; only used for create operation (optional)
   * @param project name of project to adopt or scratch; only used for adopt and scratch operations (optional)
   * @return Option[Unit]
   */
  def manageTrialProjects(
    operation: String = "count",
    count: Option[Integer] = None,
    project: Option[String] = None
    ): Request[Either[ResponseError[io.circe.Error],Unit],Nothing] = {
    assert(operation != null, "Missing required parameter 'operation' when calling TrialFireCloudCreditsApi->manageTrialProjects")

    basicRequest
      .post(uri"https://localhost/api/trial/manager/projects?operation=${operation}?count=${count}?project=${project}")
      .response(asJson[Unit])
  }

  /**
   * Update user status in the FireCloud Free Credits Program; for managers of the Program only.
   * * **Enable** Add registered user(s) to FireCloud&#39;s internal record of users that are allowed to enroll (of their own accord, by clicking the Enroll button in the UI) * **Disable** If a user has not yet enrolled themselves, we can still remove them from the trial. Reverse of the Enable operation. * **Terminate** End the user&#39;s trial by removing their billing project from the FireCloud Free Credits Program billing account. The user will no longer be able to create workspaces in that billing project. 
   *
   * @param operation operation to perform on the specified users. 
   * @param users list of users on which to operate 
   * @return Option[TrialOperationResponse]
   */
  def updateTrialUserStatus(
    operation: String,
    users: StringArray): Request[Either[ResponseError[io.circe.Error],TrialOperationResponse],Nothing] = {
    assert(operation != null, "Missing required parameter 'operation' when calling TrialFireCloudCreditsApi->updateTrialUserStatus")
    assert(users != null, "Missing required parameter 'users' when calling TrialFireCloudCreditsApi->updateTrialUserStatus")

    basicRequest
      .post(uri"https://localhost/api/trial/manager/${operation}")
      .body(users)
      .response(asJson[TrialOperationResponse])
  }

}

