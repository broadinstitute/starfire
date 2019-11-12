package org.broadinstitute.startfire.app.silk

sealed trait Argument extends SilkElement {
  def expression: Expression
}

object Argument {

  case class PositionalArgument(expression: Expression) extends Argument {
    override def asSilkCode: String = expression.asSilkCode
  }

  case class NamedArgument(id: Identifier, expression: Expression) extends Argument {
    override def asSilkCode: String = id.asSilkCode + "=" + expression.asSilkCode
  }

}
