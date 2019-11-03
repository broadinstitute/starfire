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

import java.io.File
import java.io.File._

import sttp.client._
import sttp.client.circe._
import io.circe.generic.auto._

import io.swagger.client.Decoders._
import io.swagger.client.Encoders._
import io.swagger.client.SttpUtils.Implicits._

class StorageApi() {

  /**
   * Get metadata about an object stored in GCS. 
   * Returns a subset of the metadata available from Google&#39;s Cloud Storage JSON API, as well as the estimated egress charge to North America. If you need the full metadata, we recommend you use Google&#39;s API directly; see https://cloud.google.com/storage/docs/json_api/v1/objects/get. 
   *
   * @param bucket Name of the bucket in which the object resides. 
   * @param `object` Name of the object. (be sure to urlencode) 
   * @return Option[Unit]
   */
  def getMetadata(
    bucket: String,
    `object`: String): Request[Either[ResponseError[io.circe.Error],Unit],Nothing] = {
    assert(bucket != null, "Missing required parameter 'bucket' when calling StorageApi->getMetadata")
    assert(`object` != null, "Missing required parameter '`object`' when calling StorageApi->getMetadata")

    basicRequest
      .get(uri"https://localhost/api/storage/${bucket}/${`object`}")
      .response(asJson[Unit])
  }

  /**
   * Download GCS object using a cookie token 
   * **Important**: this file download does not work within the swagger UI. It is here for documentation purposes. You can fill in the fields and submit; once you do so, you will get an error, but swagger will populate the \&quot;Request URL\&quot; field. You can then copy and paste that field into a new tab to achieve your download. 
   *
   * @param bucket Name of the bucket in which the object resides. 
   * @param `object` Name of the object. (be sure to urlencode) 
   * @return Option[File]
   */
  def getStorageDownload(
    bucket: String,
    `object`: String): Request[Either[ResponseError[io.circe.Error],File],Nothing] = {
    assert(bucket != null, "Missing required parameter 'bucket' when calling StorageApi->getStorageDownload")
    assert(`object` != null, "Missing required parameter '`object`' when calling StorageApi->getStorageDownload")

    basicRequest
      .get(uri"https://localhost/cookie-authed/download/b/${bucket}/o/${`object`}")
      .response(asJson[File])
  }

}

