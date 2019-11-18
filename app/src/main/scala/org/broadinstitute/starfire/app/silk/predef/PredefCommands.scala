package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.api.{ProfileApi, StatusApi}
import org.broadinstitute.starfire.app.silk.SilkCommand.Parameter
import org.broadinstitute.starfire.app.silk.SilkConfig.sttpBackend
import org.broadinstitute.starfire.app.silk.SilkType.SilkStringType
import org.broadinstitute.starfire.app.silk.SilkValue.{SilkIntegerValue, SilkObjectValue, SilkStringValue}
import org.broadinstitute.starfire.app.silk.{Identifier, SilkCommand, SilkError}
import org.broadinstitute.starfire.app.silk.predef.PredefUtils.Implicits._
import org.broadinstitute.starfire.model.Profile
import org.broadinstitute.starfire.utils.HttpUtils
import org.joda.time.DateTime

object PredefCommands {

  val statusStatus: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 15, 12, 52, 44, "status.status")

    override def parameters: Seq[Parameter] = Seq.empty

    override def execute(env: SilkObjectValue): Either[SilkError, SilkObjectValue] = {
      val errorOrStatus = HttpUtils.send(StatusApi.status())
      errorOrStatus.map { systemStatus =>
        println(systemStatus)
        SilkObjectValue.empty
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

  val silkDebugDump: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 15, 17, 6, 19, "silk.debug.dump")

    override def parameters: Seq[Parameter] = Seq.empty

    override def execute(env: SilkObjectValue): Either[SilkError, SilkObjectValue] = {
      for(entry <- env.entries) {
        println(entry.asReadableString)
      }
      Right(SilkObjectValue.empty)
    }
  }

  val profileSetProfile: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 17, 12, 8, 50, "profile.setProfile")

    val firstNameParam: Parameter =
      Parameter(CommonIds.starfireAccount / "firstName", SilkStringType, isRequired = true)
    val lastNameParam: Parameter =
      Parameter(CommonIds.starfireAccount / "lastName", SilkStringType, isRequired = true)
    val titleParam: Parameter =
      Parameter(CommonIds.starfireAccount / "title", SilkStringType, isRequired = true)
    val instituteParam: Parameter =
      Parameter(CommonIds.starfireAccount / "institute", SilkStringType, isRequired = true)
    val institutionalProgramParam: Parameter =
      Parameter(CommonIds.starfireAccount / "institutionalProgram", SilkStringType, isRequired = true)
    val programLocationCityParam: Parameter =
      Parameter(CommonIds.starfireAccount / "programLocationCity", SilkStringType, isRequired = true)
    val programLocationStateParam: Parameter =
      Parameter(CommonIds.starfireAccount / "programLocationState", SilkStringType, isRequired = true)
    val programLocationCountryParam: Parameter =
      Parameter(CommonIds.starfireAccount / "programLocationCountry", SilkStringType, isRequired = true)
    val piParam: Parameter =
      Parameter(CommonIds.starfireAccount / "pi", SilkStringType, isRequired = true)
    val nonProfitStatusParam: Parameter =
      Parameter(CommonIds.starfireAccount / "nonProfitStatus", SilkStringType, isRequired = true)

    override def parameters: Seq[Parameter] =
      Seq(
        CommonParameters.accountEmail, CommonParameters.accountKeyFile, firstNameParam, lastNameParam, titleParam,
        instituteParam, institutionalProgramParam, programLocationCityParam, programLocationStateParam,
        programLocationCountryParam, piParam, nonProfitStatusParam
      )

    override def execute(env: SilkObjectValue): Either[SilkError, SilkObjectValue] = {

      val profile = Profile(
        firstName = env.getString(firstNameParam.id).get,
        lastName = env.getString(lastNameParam.id).get,
        title = env.getString(titleParam.id).get,
        institute = env.getString(instituteParam.id).get,
        institutionalProgram = env.getString(institutionalProgramParam.id).get,
        programLocationCity = env.getString(programLocationCityParam.id).get,
        programLocationState = env.getString(programLocationStateParam.id).get,
        programLocationCountry = env.getString(programLocationCountryParam.id).get,
        pi = env.getString(piParam.id).get,
        nonProfitStatus = env.getString(nonProfitStatusParam.id).get,
      )
      val request = ProfileApi.setProfile(Some(profile))
      HttpUtils.send(request).map { _ =>
        SilkObjectValue.empty
      }
    }
  }

  val all: Set[SilkCommand] = Set(statusStatus, helloWorld, silkUtilGetTime, set, silkDebugDump, profileSetProfile)
  val allByRef: Map[SilkCommand.Ref, SilkCommand] = all.map(command => (command.ref, command)).toMap

  def get(ref: SilkCommand.Ref): Option[SilkCommand] = allByRef.get(ref)

}
