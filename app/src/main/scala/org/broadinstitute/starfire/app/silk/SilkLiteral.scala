package org.broadinstitute.starfire.app.silk

import org.broadinstitute.starfire.app.silk.SilkValue.{SilkFloatValue, SilkIntegerValue, SilkPrimitiveValue, SilkStringValue}

trait SilkLiteral[T] extends Expression {
  def value: T
  def asValue: SilkPrimitiveValue[T]
}

object SilkLiteral {

  case class SilkIntegerLiteral(value: Long) extends SilkLiteral[Long] {
    override def asSilkCode: String = value.toString

    override def asValue: SilkIntegerValue = SilkIntegerValue(value)
  }

  case class SilkFloatLiteral(value: Double) extends SilkLiteral[Double] {
    override def asSilkCode: String = {
      val simpleString = value.toString
      if(simpleString.forall(char => char.isDigit || char == '+' || char == '-')) {
        simpleString + ".0"
      } else {
        simpleString
      }
    }

    override def asValue: SilkFloatValue = SilkFloatValue(value)
  }

  case class SilkStringLiteral(value: String) extends SilkLiteral[String] {
    override def asSilkCode: String = {
      val charsEscaped = value.flatMap { char: Char =>
        char match {
          case char: Char if SilkStringLiteral.escapableChars(char) => Seq('\\', char)
          case char: Char => Seq(char)
        }
      }
      "\"" + new String(charsEscaped.toArray) + "\""
    }

    override def asValue: SilkStringValue = SilkStringValue(value)
  }

  object SilkStringLiteral {
    val escapableChars: Set[Char] = Set('"', '\\')
  }

}
