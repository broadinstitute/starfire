package org.broadinstitute.startfire.app.silkie

import org.broadinstitute.startfire.app.silkie.Argument.{NamedArgument, PositionalArgument}

sealed trait Statement extends SilkieElement {

}

object Statement {

  case class Command(identifier: Identifier,
                     positionalArguments: Seq[PositionalArgument],
                     namedArguments: Seq[NamedArgument]) extends Statement {
    def arguments: Seq[Argument] = positionalArguments ++ namedArguments

    override def asSilkieCode: String = (identifier +: arguments).map(_.asSilkieCode).mkString(" ")
  }

}

