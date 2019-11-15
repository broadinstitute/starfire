package org.broadinstitute.starfire.app.silk

case class SilkError(message: String, causeOpt: Option[SilkError]) {

  def allMessages: String = {
    causeOpt match {
      case Some(cause) => message + "\n" + cause.allMessages
      case None => message
    }
  }
}

object SilkError {
  def apply(message: String): SilkError = SilkError(message, None)

  def apply(message: String, cause: SilkError): SilkError = SilkError(message, Some(cause))
}

