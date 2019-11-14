package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.api.StatusApi
import org.broadinstitute.starfire.app.silk.SilkCommand.Parameter
import org.broadinstitute.starfire.app.silk.SilkConfig.sttpBackend
import org.broadinstitute.starfire.app.silk.SilkValue.SilkObjectValue
import org.broadinstitute.starfire.app.silk.{Error, SilkCommand}

object PredefCommands {

  val statusStatus: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref.fromString("9bb9643e-0085-4c35-96d9-c7681ef20ee4")

    override def parameters: Seq[Parameter] = Seq.empty

    override def execute(env: SilkObjectValue): Either[Error, SilkObjectValue] = {
      StatusApi.status().send().body match {
        case Left(responseError) => Left(Error(responseError.getMessage))
        case Right(systemStatus) =>
          println(systemStatus)
          Right(SilkObjectValue.empty)
      }
    }
  }

  val all: Set[SilkCommand] = Set(statusStatus)
  val allByRef: Map[SilkCommand.Ref, SilkCommand] = all.map(command => (command.ref, command)).toMap

  def get(ref: SilkCommand.Ref): Option[SilkCommand] = allByRef.get(ref)

}
