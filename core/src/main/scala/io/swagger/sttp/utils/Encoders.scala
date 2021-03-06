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

package io.swagger.sttp.utils

import io.circe.{Encoder, Json}
import io.circe.generic.semiauto._
import java.util.Date
import better.files.File

object Encoders {
  implicit val dateEncoder: Encoder[Date] = new Encoder[Date] {
    override def apply(date: Date): Json = Json.fromString(date.toString)
  }
  implicit val fileEncoder: Encoder[File] = new Encoder[File] {
    override def apply(file: File): Json = Json.fromString(file.toString)
  }
}