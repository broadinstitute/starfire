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

import sttp.client._
import sttp.client.circe._
import io.circe.generic.auto._

import io.swagger.sttp.utils.Decoders._
import io.swagger.sttp.utils.Encoders._
import io.swagger.sttp.utils.SttpUtils.Implicits._

object ShareLogApi {

  /**
   * Get a list of users with whom current user has shared a Workspace. 
   * Get a list of users with whom current user has shared a Workspace. 
   *
   * @return Option[StringArray]
   */
  def getSharees(
    ): Request[Either[ResponseError[io.circe.Error],StringArray],Nothing] = {

    basicRequest
      .get(uri"https://api.firecloud.org/api/sharelog/sharees")
      .response(asJson[StringArray])
  }

}

