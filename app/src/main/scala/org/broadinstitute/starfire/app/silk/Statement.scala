package org.broadinstitute.starfire.app.silk

import Argument.{NamedArgument, PositionalArgument}

case class Statement(identifier: Identifier,
                     positionalArguments: Seq[PositionalArgument],
                     namedArguments: Seq[NamedArgument]) extends SilkElement {
  def arguments: Seq[Argument] = positionalArguments ++ namedArguments

  override def asSilkCode: String = (identifier +: arguments).map(_.asSilkCode).mkString(" ")
}


