package org.broadinstitute.starfire.app.silk

import org.broadinstitute.starfire.app.silk.SilkValue.{SilkFloatValue, SilkIntegerValue, SilkPrimitiveValue, SilkStringValue, SilkTypedPrimitiveValue}

trait SilkLiteral extends Expression {
  def value: Any
  def asValue: SilkPrimitiveValue
}

object SilkLiteral {

  trait SilkTypedLiteral[T] extends SilkLiteral {
    def value: T
    def asValue: SilkTypedPrimitiveValue[T]
  }

  case class SilkIntegerLiteral(value: Long) extends SilkTypedLiteral[Long] {
    override def asSilkCode: String = value.toString

    override def asValue: SilkIntegerValue = SilkIntegerValue(value)
  }

  case class SilkFloatLiteral(value: Double) extends SilkTypedLiteral[Double] {
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

  case class SilkStringLiteral(value: String) extends SilkTypedLiteral[String] {
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
