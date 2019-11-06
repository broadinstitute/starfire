package org.broadinstitute.startfire.app.silkie

sealed trait Statement {

}

object Statement {
  def parse(string: String): Either[Error, Statement] = {
    ???
  }

}

case class Command(identifier: Identifier, arguments: Seq[Argument]) extends Statement {

}