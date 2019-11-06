package org.broadinstitute.startfire.app.silkie

case class Identifier(parentOpt: Option[Identifier], name: String) {
  def /(childName: String): Identifier = Identifier(Some(this), childName)

  def asString: String = parentOpt match {
    case Some(parent) => parent.asString + "." + name
    case None => name
  }
}

object Identifier {
  def apply(name: String): Identifier = Identifier(None, name)

  def fromNonEmptyList(parts: Seq[String]): Identifier = {
    parts match {
      case Seq(name) => Identifier(name)
      case _ => fromNonEmptyList(parts.dropRight(1)) / parts.last
    }
  }

  def apply(parts: Seq[String]): Either[Error, Identifier] = {
    if (parts.isEmpty) {
      Left(Error("Wanted to build identifier, but got empty list of parts."))
    } else {
      Right(fromNonEmptyList(parts))
    }
  }

  def canContain(char: Char): Boolean = Character.isJavaIdentifierPart(char)

  def canStartWith(char: Char): Boolean = Character.isJavaIdentifierStart(char)

  val partSep: Char = '.'
}