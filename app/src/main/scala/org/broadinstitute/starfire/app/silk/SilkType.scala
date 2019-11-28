package org.broadinstitute.starfire.app.silk

sealed trait SilkType {
  def name: String
  def canBeUsedAs(oType: SilkType): Boolean
}

object SilkType {

  object SilkAny extends SilkType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny

    override def name: String = "Any"
  }

  sealed trait SilkPrimitiveType extends SilkType

  object SilkStringType extends SilkPrimitiveType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny || oType == SilkStringType

    override def name: String = "String"
  }

  object SilkIntegerType extends SilkPrimitiveType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny || oType == SilkIntegerType

    override def name: String = "Integer"
  }

  object SilkFloatType extends SilkPrimitiveType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny || oType == SilkFloatType

    override def name: String = "Float"
  }

  object SilkObjectType extends SilkType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny || oType == SilkObjectType

    override def name: String = "Object"
  }

  object SilkCommandType extends SilkType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny || oType == SilkCommandType

    override def name: String = "Command"
  }

}