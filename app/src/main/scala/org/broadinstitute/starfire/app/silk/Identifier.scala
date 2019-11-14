package org.broadinstitute.starfire.app.silk

case class Identifier(parentOpt: Option[Identifier], name: String) extends Expression {
  def /(childName: String): Identifier = Identifier(Some(this), childName)

  def asString: String = parentOpt match {
    case Some(parent) => parent.asString + "." + name
    case None => name
  }

  override def asSilkCode: String = asString

  def isSinglePart: Boolean = parentOpt.isEmpty

  def head: String = {
    parentOpt match {
      case Some(parent) => parent.head
      case None => name
    }
  }

  def tailOpt: Option[Identifier] = {
    parentOpt match {
      case Some(parent) =>
        parent.tailOpt match {
          case Some(parentTail) => Some(Identifier(parentTail, name))
          case None => Some(Identifier(name))
        }
      case None => None
    }
  }
}

object Identifier {
  def apply(name: String): Identifier = Identifier(None, name)
  def apply(parent: Identifier, name: String): Identifier = Identifier(Some(parent), name)

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