package org.broadinstitute.startfire.app.silkie

sealed trait Argument {
  def id: Identifier
}

object Argument {
  sealed trait LiteralArgument[T] extends Argument {
    def value: T
  }
  case class IntegerArgument(id: Identifier, value: Long) extends LiteralArgument[Long]
  case class FloatArgument(id: Identifier, value: Double) extends LiteralArgument[Double]
  case class StringArgument(id: Identifier, value: String) extends LiteralArgument[String]
  case class IdentifierArgument(id: Identifier, rhsId: Identifier) extends Argument
}
