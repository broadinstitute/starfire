package org.broadinstitute.starfire.util

case class SnagOld(message: String, causeOpt: Option[SnagOld]) extends Snag {
  def addPrefix(prefix: String): SnagOld = copy(message = prefix + ": " + message)

  def allMessages: String = {
    causeOpt match {
      case Some(cause) => message + "\n" + cause.allMessages
      case None => message
    }
  }
}

object SnagOld {
  def apply(message: String): SnagOld = SnagOld(message, None)

  def apply(message: String, cause: SnagOld): SnagOld = SnagOld(message, Some(cause))

  def apply(throwable: Throwable): SnagOld = {
    val cause = throwable.getCause
    if (cause != null) {
      SnagOld(throwable.getMessage, Some(SnagOld(cause)))
    } else {
      SnagOld(throwable.getMessage)
    }
  }
}

