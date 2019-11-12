package org.broadinstitute.startfire.app.silk

import org.broadinstitute.startfire.app.silk.Argument.{NamedArgument, PositionalArgument}

sealed trait Statement extends SilkElement {

}

object Statement {

  case class Command(identifier: Identifier,
                     positionalArguments: Seq[PositionalArgument],
                     namedArguments: Seq[NamedArgument]) extends Statement {
    def arguments: Seq[Argument] = positionalArguments ++ namedArguments

    override def asSilkCode: String = (identifier +: arguments).map(_.asSilkCode).mkString(" ")
  }

}

