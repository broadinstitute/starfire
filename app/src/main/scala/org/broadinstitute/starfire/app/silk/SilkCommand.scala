package org.broadinstitute.starfire.app.silk

import org.broadinstitute.starfire.app.silk.SilkCommand.{Parameter, Ref}
import org.broadinstitute.starfire.app.silk.SilkValue.SilkObjectValue
import org.broadinstitute.starfire.util.Snag
import org.broadinstitute.starfire.utils.StringToLongHash
import org.joda.time.DateTime

trait SilkCommand {
  def ref: Ref

  def parameters: Seq[Parameter]

  def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue]
}

object SilkCommand {

  case class Ref(time: Long, hash: Long)

  object Ref {
    def apply(dateTime: DateTime, string: String): Ref = Ref(dateTime.getMillis, StringToLongHash.hash(string))

    def apply(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int, string: String): Ref =
      apply(new DateTime(year, month, day, hour, minute, second), string)
  }

  case class Parameter(id: Identifier, silkType: SilkType, isRequired: Boolean)

}
