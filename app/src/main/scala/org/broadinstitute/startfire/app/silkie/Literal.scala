package org.broadinstitute.startfire.app.silkie

trait Literal[T] extends Expression {
  def value: T
}

object Literal {

  case class IntegerLiteral(value: Long) extends Literal[Long] {
    override def asSilkieCode: String = value.toString
  }

  case class StringLiteral(value: String) extends Literal[String] {
    override def asSilkieCode: String =
      "\"" + value.flatMap { char: Char =>
        char match {
          case char: Char if StringLiteral.escapableChars(char) => Seq("\\", char)
          case char: Char => Seq(char)
        }
      } + "\""
  }

  object StringLiteral {
    val escapableChars: Set[Char] = Set('"', '\\')
  }

}
