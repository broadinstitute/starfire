package org.broadinstitute.starfire.app.silk

case class Error(message: String, causeOpt: Option[Error]) {

  def allMessages: String = {
    causeOpt match {
      case Some(cause) => message + "\n" + cause.allMessages
      case None => message
    }
  }
}

object Error {
  def apply(message: String): Error = Error(message, None)

  def apply(message: String, cause: Error): Error = Error(message, Some(cause))
}

