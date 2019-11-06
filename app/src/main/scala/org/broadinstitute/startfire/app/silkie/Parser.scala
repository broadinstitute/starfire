package org.broadinstitute.startfire.app.silkie

object Parser {


  def consumeIdentifier(string: String): Either[Error, Result[Identifier]] = {
    var char = string.charAt(0)
    if(!Identifier.canStartWith(char)) {
      Left(Error(s"Identifier cannot start with '$char'"))
    } else {
      var idString: String = ""
      var remainder: String = string
      while(Identifier.canContain(char) || char == '.') {
        idString += char
        remainder = remainder.drop(1)
        char = remainder.charAt(0)
      }
      if(idString.contains("..")) {
        Left(Error("Identifier cannot contain two dots in a row."))
      } else if(idString.endsWith(".")) {
        Left(Error("Identifier cannot end with a dot."))
      } else {
        val idParts = idString.split("\\.")
        Identifier(idParts).map(Result(_, remainder))
      }
   }
  }

  def consumeWhitespace(string: String): Either[Error, Result[Nothing]] = {
    val char0 = string.charAt(0)
    if(Character.isWhitespace(char0)) {
      // Right(Result(string.trim))
      ???
    } else {
      Left(Error(s"Expected whitespace, but got '$char0'."))
    }
  }

  def consumeArgument(string: String): Either[Error, Result[Argument]] = {
    consumeIdentifier(string).flatMap { lhsId =>
      ???
    }
    // val lhsId =
  }

  def parse(string: String): Either[Error, Statement] = {
    ???
  }

  case class Result[T](value: T, remainder: String)
}
