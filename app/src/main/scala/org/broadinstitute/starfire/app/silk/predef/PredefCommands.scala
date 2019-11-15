package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.api.StatusApi
import org.broadinstitute.starfire.app.silk.SilkCommand.Parameter
import org.broadinstitute.starfire.app.silk.SilkConfig.sttpBackend
import org.broadinstitute.starfire.app.silk.SilkType.SilkStringType
import org.broadinstitute.starfire.app.silk.SilkValue.{SilkIntegerValue, SilkObjectValue, SilkStringValue}
import org.broadinstitute.starfire.app.silk.{SilkError, Identifier, SilkCommand}
import org.broadinstitute.starfire.app.silk.predef.PredefUtils.Implicits._
import org.joda.time.DateTime

object PredefCommands {

  val statusStatus: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 15, 12, 52, 44, "status.status")

    override def parameters: Seq[Parameter] = Seq.empty

    override def execute(env: SilkObjectValue): Either[SilkError, SilkObjectValue] = {
      StatusApi.status().send().body match {
        case Left(responseError) => Left(SilkError(responseError.getMessage))
        case Right(systemStatus) =>
          println(systemStatus)
          Right(SilkObjectValue.empty)
      }
    }
  }

  val helloWorld: SilkCommand = new SilkCommand {
    val addresseeId: Identifier = "hello" / "world" / "addressee"
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 15, 12, 54, 4, "hello.world")

    override def parameters: Seq[Parameter] =
      Seq(Parameter(addresseeId, SilkStringType, isRequired = true))

    override def execute(env: SilkObjectValue): Either[SilkError, SilkObjectValue] = {
      val addressee = env.get(addresseeId).get.asInstanceOf[SilkStringValue].value
      println(s"Hello, $addressee!")
      Right(SilkObjectValue.empty)
    }
  }

  val silkUtilGetTime: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 15, 13, 38, 40, "silk.util.getTime")

    override def parameters: Seq[Parameter] = Seq.empty

    override def execute(env: SilkObjectValue): Either[SilkError, SilkObjectValue] = {
      val timeNowMillis = System.currentTimeMillis()
      val dateTimeNow = new DateTime(timeNowMillis)
      val dateTimeString =  dateTimeNow.toString()
      Right(SilkObjectValue(
        "dateTime" -> SilkIntegerValue(timeNowMillis),
        "dateTimeString" -> SilkStringValue(dateTimeString)
      ))
    }
  }

  val set: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 15, 15, 3, 18, "set")

    override def parameters: Seq[Parameter] = Seq.empty

    override def execute(env: SilkObjectValue): Either[SilkError, SilkObjectValue] = {
      Right(env)
    }
  }

  val all: Set[SilkCommand] = Set(statusStatus, helloWorld, silkUtilGetTime, set)
  val allByRef: Map[SilkCommand.Ref, SilkCommand] = all.map(command => (command.ref, command)).toMap

  def get(ref: SilkCommand.Ref): Option[SilkCommand] = allByRef.get(ref)

}
