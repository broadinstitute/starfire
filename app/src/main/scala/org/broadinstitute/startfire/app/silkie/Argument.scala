package org.broadinstitute.startfire.app.silkie

sealed trait Argument extends SilkieElement {
  def expression: Expression
}

object Argument {

  case class PositionalArgument(expression: Expression) extends Argument {
    override def asSilkieCode: String = expression.asSilkieCode
  }

  case class NamedArgument(id: Identifier, expression: Expression) extends Argument {
    override def asSilkieCode: String = id.asSilkieCode + "=" + expression.asSilkieCode
  }

}
