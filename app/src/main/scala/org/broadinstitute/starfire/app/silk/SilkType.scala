package org.broadinstitute.starfire.app.silk

sealed trait SilkType {
  def canBeUsedAs(oType: SilkType): Boolean
}

object SilkType {

  object SilkAny extends SilkType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny
  }

  sealed trait SilkPrimitiveType extends SilkType

  object SilkStringType extends SilkPrimitiveType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny || oType == SilkStringType
  }

  object SilkIntegerType extends SilkPrimitiveType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny || oType == SilkIntegerType
  }

  object SilkFloatType extends SilkPrimitiveType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny || oType == SilkFloatType
  }

  object SilkObjectType extends SilkType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny || oType == SilkObjectType
  }

  object SilkCommandType extends SilkType {
    override def canBeUsedAs(oType: SilkType): Boolean = oType == SilkAny || oType == SilkCommandType
  }

}