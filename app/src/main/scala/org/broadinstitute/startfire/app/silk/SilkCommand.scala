package org.broadinstitute.startfire.app.silk

import java.util.UUID

import org.broadinstitute.startfire.app.silk.SilkCommand.{Parameter, Ref}
import org.broadinstitute.startfire.app.silk.SilkValue.SilkObjectValue

trait SilkCommand {
  def ref: Ref
  def parameters: Seq[Parameter]
  def execute(env: SilkObjectValue): Either[Error, SilkObjectValue]
}

object SilkCommand {
  case class Ref(uuid: UUID)
  object Ref {
    def create: Ref = Ref(UUID.randomUUID())
  }
  case class Parameter(id: Identifier, silkType: SilkType, isRequired: Boolean)
}
