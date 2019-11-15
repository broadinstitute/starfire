package org.broadinstitute.starfire.app.silk

import fastparse.NoWhitespace._
import fastparse._
import org.broadinstitute.starfire.app.silk.Argument.{NamedArgument, PositionalArgument}
import org.broadinstitute.starfire.app.silk.SilkLiteral.{SilkIntegerLiteral, SilkStringLiteral}

object Parser {

  object ElementParsers {
    def identifierPartStartChar[_: P]: P[String] = P(CharPred(Character.isJavaIdentifierStart).!)

    def identifierPartAnywhereChar[_: P]: P[String] = P(CharPred(Character.isJavaIdentifierPart).!)

    def identifierPartSeparator[_: P]: P[Unit] = P(".")

    def identifierChar[_: P]: P[Any] = P(identifierPartAnywhereChar | identifierPartSeparator)

    def identifierPart[_: P]: P[String] =
      P(identifierPartStartChar ~ identifierPartAnywhereChar.rep ~ !identifierPartAnywhereChar).map {
        case (startChar, otherChars) => startChar + otherChars.mkString("")
      }

    def identifier[_: P]: P[Identifier] =
      P(identifierPart ~ (identifierPartSeparator ~ identifierPart).rep ~ !identifierChar).map {
        case (firstPart, moreParts) => Identifier.fromNonEmptyList(firstPart +: moreParts)
      }

    def whitespace[_: P]: P[Unit] = P(CharsWhile(Character.isWhitespace))

    def integerLiteral[_: P]: P[SilkIntegerLiteral] =
      P((CharIn("+\\-").? ~ CharIn("1-9") ~ CharIn("0-9").rep ~ !CharIn("0-9.")).!).map(_.toLong).map(SilkIntegerLiteral)

    def stringNotEscapedChar[_: P]: P[String] = P(CharPred(!SilkStringLiteral.escapableChars.contains(_)).!)

    def stringEscape[_: P]: P[String] = P("\\" ~ CharPred(SilkStringLiteral.escapableChars).!)

    def stringLiteral[_: P]: P[SilkStringLiteral] =
      P("\"" ~ (stringEscape | stringNotEscapedChar).rep ~ "\"").map(_.mkString("")).map(SilkStringLiteral(_))

    def expression[_: P]: P[Expression] = P(identifier | integerLiteral | stringLiteral)

    def positionalArgument[_: P]: P[PositionalArgument] = P(expression ~ !"=").map(PositionalArgument)

    def namedArgument[_: P]: P[NamedArgument] = P(identifier ~ "=" ~ expression).map {
      case (identifier, expression) => NamedArgument(identifier, expression)
    }

    def command[_: P]: P[Statement] =
      P(identifier ~ (whitespace ~ positionalArgument).rep ~ (whitespace ~ namedArgument).rep).map {
        case (identifier, positionalArguments, namedArguments) =>
          Statement(identifier, positionalArguments, namedArguments)
      }

    def commandLine[_: P]: P[Statement] = P(Start ~ whitespace.? ~ command ~ whitespace.? ~ End)
  }

  def parseCommandLine(string: String): Either[SilkError, Statement] = {
    parse(string, ElementParsers.commandLine(_)) match {
      case Parsed.Success(statement, _) => Right(statement)
      case failure: Parsed.Failure =>
        val tracedFailure = failure.trace()
        Left(SilkError("Parse failure", SilkError(tracedFailure.longMsg)))
    }
  }


}