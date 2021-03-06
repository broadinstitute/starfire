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

import io.swagger.sttp.utils.Decoders._
import io.swagger.sttp.utils.Encoders._
import io.circe.generic.JsonCodec

@JsonCodec case class MethodRepoMethod (
  // The namespace of the method in Agora
  methodNamespace: String,
  // The name of the method in Agora
  methodName: String,
  // The integer method version in Agora (or a string method version for Dockstore)
  methodVersion: Integer,
  // The path of the method in Dockstore
  methodPath: Option[String] = None,
  // The method's repository, currently one of \"agora\" or \"dockstore\"
  sourceRepo: Option[String] = None,
  // URI that specifies the method's repository and captures all the information necessary to identify the method within its repository (e.g. agora://namespace/name/1, dockstore://path/version)
  methodUri: Option[String] = None
)

