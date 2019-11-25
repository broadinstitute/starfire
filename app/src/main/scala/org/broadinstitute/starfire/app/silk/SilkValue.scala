package org.broadinstitute.starfire.app.silk

import org.broadinstitute.starfire.app.silk.SilkLiteral.{SilkFloatLiteral, SilkIntegerLiteral, SilkStringLiteral, SilkTypedLiteral}
import org.broadinstitute.starfire.app.silk.SilkType._
import org.broadinstitute.starfire.app.silk.SilkValue.SilkObjectValue.Entry

trait SilkValue {
  def silkType: SilkType
  def asReadableString: String
}

object SilkValue {

  sealed trait SilkPrimitiveValue extends SilkValue {
    override def silkType: SilkType.SilkPrimitiveType

    def value: Any

    def asLiteral: SilkLiteral

    override def asReadableString: String = asLiteral.asSilkCode
  }

  sealed trait SilkTypedPrimitiveValue[T] extends SilkPrimitiveValue {
    override def silkType: SilkType.SilkPrimitiveType

    def value: T

    def asLiteral: SilkTypedLiteral[T]

    override def asReadableString: String = asLiteral.asSilkCode
  }

  case class SilkStringValue(value: String) extends SilkTypedPrimitiveValue[String] {
    override def silkType: SilkStringType.type = SilkStringType

    override def asLiteral: SilkStringLiteral = SilkStringLiteral(value)
  }

  case class SilkIntegerValue(value: Long) extends SilkTypedPrimitiveValue[Long] {
    override def silkType: SilkIntegerType.type = SilkIntegerType

    override def asLiteral: SilkIntegerLiteral = SilkIntegerLiteral(value)
  }

  case class SilkFloatValue(value: Double) extends SilkTypedPrimitiveValue[Double] {
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

    def ++(oObj: SilkObjectValue): SilkObjectValue = {
      var objNew: SilkObjectValue = this
      for(entry <- oObj.entries) {
        objNew = objNew.add(entry.id, entry.value)
      }
      objNew
    }

    def remove(key: String): SilkObjectValue = {
      SilkObjectValue(keys.filter(_ != key), values - key)
    }

    def -(key: String): SilkObjectValue = remove(key)

    def add(id: Identifier, value: SilkValue): SilkObjectValue = {
      id.tailOpt match {
        case None => add(id.name, value)
        case Some(idTail) =>
          val idHead = id.head
          val childObject = values.get(idHead) match {
            case Some(child: SilkObjectValue) => child
            case _ => SilkObjectValue.empty
          }
          add(idHead, childObject.add(idTail, value))
      }
    }

    def remove(id: Identifier): SilkObjectValue = {
      id.tailOpt match {
        case None => remove(id.name)
        case Some(idTail) =>
          val idHead = id.head
          values.get(idHead) match {
            case Some(childObject: SilkObjectValue) => add(idHead, childObject.remove(idTail))
            case _ => this
          }
      }
    }

    def get(key: String): Option[SilkValue] = values.get(key)

    def get(id: Identifier): Option[SilkValue] = {
      id.tailOpt match {
        case None => get(id.name)
        case Some(idTail) =>
          val idHead = id.head
          values.get(idHead) match {
            case Some(childObject: SilkObjectValue) => childObject.get(idTail)
            case _ => None
          }
      }
    }

    def getString(id: Identifier): Option[String] = {
      get(id) match {
        case Some(SilkStringValue(string)) => Some(string)
        case _ => None
      }
    }

    def getInteger(id: Identifier): Option[Long] = {
      get(id) match {
        case Some(SilkIntegerValue(longValue)) => Some(longValue)
        case Some(SilkFloatValue(doubleValue)) => Some(doubleValue.toLong)
        case _ => None
      }
    }

    def getFloat(id: Identifier): Option[Double] = {
      get(id) match {
        case Some(SilkIntegerValue(longValue)) => Some(longValue.toDouble)
        case Some(SilkFloatValue(doubleValue)) => Some(doubleValue)
        case _ => None
      }
    }

    def isEmpty: Boolean = keys.isEmpty

    def entries: Iterator[Entry] = {
      keys.iterator.flatMap { key =>
        val value = values(key)
        value match {
          case obj: SilkObjectValue => obj.entries.map(entry => Entry(entry.id.prepend(key), entry.value))
          case primitiveValue: SilkPrimitiveValue => Iterator.single(Entry(Identifier(key), primitiveValue))
          case _ => Iterator.empty
        }
      }
    }

    override def asReadableString: String = entries.map(_.asReadableString).mkString("{\n", "\n", "\n}")
  }

  object SilkObjectValue {
    case class Entry(id: Identifier, value: SilkPrimitiveValue) {
      def asReadableString: String = id.asSilkCode + "=" + value.asReadableString
      def asSetStatement: String = "set " + id.asSilkCode + "=" + value.asLiteral.asSilkCode
    }
    val empty: SilkObjectValue = new SilkObjectValue(Seq.empty, Map.empty)

    def apply(idValuePair: (Identifier, SilkValue), idValuePairs: (Identifier, SilkValue)*): SilkObjectValue = {
      val allPairs = idValuePair +: idValuePairs
      var silkObjectValue: SilkObjectValue = SilkObjectValue.empty
      for((id, value) <- allPairs) {
        silkObjectValue = silkObjectValue.add(id, value)
      }
      silkObjectValue
    }
  }

  case class SilkCommandValue(ref: SilkCommand.Ref) extends SilkValue {
    override def silkType: SilkCommandType.type = SilkCommandType

    override def asReadableString: String = s"Command(${ref.time}, ${ref.hash})"
  }

}
