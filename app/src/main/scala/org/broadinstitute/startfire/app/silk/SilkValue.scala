package org.broadinstitute.startfire.app.silk

import org.broadinstitute.startfire.app.silk.SilkLiteral.{SilkFloatLiteral, SilkIntegerLiteral, SilkStringLiteral}
import org.broadinstitute.startfire.app.silk.SilkType.{SilkFloatType, SilkIntegerType, SilkObjectType, SilkStringType}

trait SilkValue {
  def silkType: SilkType
}

object SilkValue {

  sealed trait SilkPrimitiveValue[T] extends SilkValue {
    override def silkType: SilkType.SilkPrimitiveType

    def value: T

    def asLiteral: SilkLiteral[T]
  }

  case class SilkStringValue(value: String) extends SilkPrimitiveValue[String] {
    override def silkType: SilkStringType.type = SilkStringType

    override def asLiteral: SilkStringLiteral = SilkStringLiteral(value)
  }

  case class SilkIntegerValue(value: Long) extends SilkPrimitiveValue[Long] {
    override def silkType: SilkIntegerType.type = SilkIntegerType

    override def asLiteral: SilkIntegerLiteral = SilkIntegerLiteral(value)
  }

  case class SilkFloatValue(value: Double) extends SilkPrimitiveValue[Double] {
    override def silkType: SilkFloatType.type = SilkFloatType

    override def asLiteral: SilkFloatLiteral = SilkFloatLiteral(value)
  }

  case class SilkObjectValue(keys: Seq[String], values: Map[String, SilkValue]) extends SilkValue {
    override def silkType: SilkObjectType.type = SilkObjectType

    def containsKey(key: String): Boolean = values.contains(key)

    def add(key: String, value: SilkValue): SilkObjectValue = {
      if (containsKey(key)) {
        SilkObjectValue(keys, values + (key -> value))
      } else {
        SilkObjectValue(keys :+ key, values + (key -> value))
      }
    }

    def +(pair: (String, SilkValue)): SilkObjectValue = {
      val (key, value) = pair
      add(key, value)
    }

    def remove(key: String): SilkObjectValue = {
      SilkObjectValue(keys.filter(_ != key), values - key)
    }

    def -(key: String): SilkObjectValue = remove(key)

    def add(id: Identifier, value: SilkValue): SilkObjectValue = {
      id.tailOpt match {
        case None => add(id.name, value)
        case Some(tail) =>
          val idHead = id.head
          val childObject = values.get(idHead) match {
            case Some(child: SilkObjectValue) => child
            case _ => SilkObjectValue.empty
          }
          add(idHead, childObject.add(tail, value))
      }
    }

    def remove(id: Identifier): SilkObjectValue = {
      ???
    }
  }

  object SilkObjectValue {
    val empty: SilkObjectValue = SilkObjectValue(Seq.empty, Map.empty)
  }

}
