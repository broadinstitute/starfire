package org.broadinstitute.startfire.app.silkie

case class Error(message: String, causeOpt: Option[Error]) {

}

object Error {
  def apply(message: String): Error = Error(message, None)

  def apply(message: String, cause: Error): Error = Error(message, Some(cause))
}

