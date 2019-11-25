package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.api.{EntitiesApi, MethodConfigurationsApi, ProfileApi, StatusApi, SubmissionsApi, WorkspacesApi}
import org.broadinstitute.starfire.app.silk.SilkCommand.Parameter
import org.broadinstitute.starfire.app.silk.SilkConfig.sttpBackend
import org.broadinstitute.starfire.app.silk.SilkType.SilkStringType
import org.broadinstitute.starfire.app.silk.SilkValue.{SilkIntegerValue, SilkObjectValue, SilkStringValue}
import org.broadinstitute.starfire.app.silk.predef.PredefUtils.Implicits._
import org.broadinstitute.starfire.app.silk.{Identifier, SilkCommand, SilkHttpUtils}
import org.broadinstitute.starfire.model.{Profile, SubmissionRequest}
import org.broadinstitute.starfire.util.Snag
import org.broadinstitute.starfire.utils.HttpUtils
import org.joda.time.DateTime

object PredefCommands {

  val statusStatus: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 15, 12, 52, 44, "status.status")

    override def parameters: Seq[Parameter] = Seq.empty

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
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

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      val addressee = env.get(addresseeId).get.asInstanceOf[SilkStringValue].value
      println(s"Hello, $addressee!")
      Right(SilkObjectValue.empty)
    }
  }

  val silkUtilGetTime: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 15, 13, 38, 40, "silk.util.getTime")

    override def parameters: Seq[Parameter] = Seq.empty

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      val timeNowMillis = System.currentTimeMillis()
      val dateTimeNow = new DateTime(timeNowMillis)
      val dateTimeString =  dateTimeNow.toString()
      Right(SilkObjectValue(
        Identifier("dateTime") -> SilkIntegerValue(timeNowMillis),
        Identifier("dateTimeString") -> SilkStringValue(dateTimeString)
      ))
    }
  }

  val set: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 15, 15, 3, 18, "set")

    override def parameters: Seq[Parameter] = Seq.empty

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      Right(env)
    }
  }

  val silkDebugDump: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 15, 17, 6, 19, "silk.debug.dump")

    override def parameters: Seq[Parameter] = Seq.empty

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
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
        CommonParameters.accountKeyFile, firstNameParam, lastNameParam, titleParam, instituteParam,
        institutionalProgramParam, programLocationCityParam, programLocationStateParam, programLocationCountryParam,
        piParam, nonProfitStatusParam
      )

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {

      val profile = Profile(
        firstName = env.getString(firstNameParam),
        lastName = env.getString(lastNameParam),
        title = env.getString(titleParam),
        institute = env.getString(instituteParam),
        institutionalProgram = env.getString(institutionalProgramParam),
        programLocationCity = env.getString(programLocationCityParam),
        programLocationState = env.getString(programLocationStateParam),
        programLocationCountry = env.getString(programLocationCountryParam),
        pi = env.getString(piParam),
        nonProfitStatus = env.getString(nonProfitStatusParam),
      )
      val request = ProfileApi.setProfile(Some(profile))
      SilkHttpUtils.sendAuthorizedPrintResponseReturnEmpty(request, env)
    }
  }

  val entitiesGetEntitiesWithType: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 19, 17, 33, 37, "entities.getEntitiesWithType")

    val workspaceNamespaceParam: Parameter = Parameter("workspaceNamespace", SilkStringType, isRequired = true)
    val workspaceNameParam: Parameter = Parameter("workspaceName", SilkStringType, isRequired = true)

    override def parameters: Seq[Parameter] =
      Seq(CommonParameters.accountKeyFile, CommonParameters.workspaceNamespace, CommonParameters.workspaceName)

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      val workspaceNamespace = env.getString(CommonParameters.workspaceNamespace)
      val workspaceName = env.getString(CommonParameters.workspaceName)
      val request = EntitiesApi.getEntitiesWithType(workspaceNamespace, workspaceName)
      SilkHttpUtils.sendAuthorizedPrintResponseReturnEmpty(request, env)
    }
  }

  val methodConfigurationsListWorkspaceMethodConfigs: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref =
      SilkCommand.Ref(2019, 11, 19, 17, 48, 57, "methodConfigurations.listWorkspaceMethodConfigs")

    override def parameters: Seq[Parameter] = Seq(CommonParameters.workspaceNamespace, CommonParameters.workspaceName)

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      val workspaceNamespace = env.getString(CommonParameters.workspaceNamespace)
      val workspaceName = env.getString(CommonParameters.workspaceName)
      val request = MethodConfigurationsApi.listWorkspaceMethodConfigs(workspaceNamespace, workspaceName)
      SilkHttpUtils.sendAuthorizedPrintResponseReturnEmpty(request, env)
    }
  }

  val submissionsCreateSubmission: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 21, 17, 1, 6, "submissions.createSubmission")

    val methodConfigurationNamespaceParam: Parameter =
      Parameter("methodConfigurationNamespace", SilkStringType, isRequired = true)
    val methodConfigurationNameParam: Parameter =
      Parameter("methodConfigurationName", SilkStringType, isRequired = true)

    override def parameters: Seq[Parameter] =
      Seq(
        CommonParameters.workspaceNamespace, CommonParameters.workspaceName, methodConfigurationNamespaceParam,
        methodConfigurationNameParam
      )

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      val workspaceNamespace = env.getString(CommonParameters.workspaceNamespace)
      val workspaceName = env.getString(CommonParameters.workspaceName)
      val methodConfigurationNamespace = env.getString(methodConfigurationNamespaceParam)
      val methodConfigurationName = env.getString(methodConfigurationNameParam)
      val submissionRequest =
        SubmissionRequest(methodConfigurationNamespace, methodConfigurationName, useCallCache = true)
      val request = SubmissionsApi.createSubmission(workspaceNamespace, workspaceName, submissionRequest)
      SilkHttpUtils.sendAuthorizedPrintResponseReturnEmpty(request, env)
    }
  }

  val submissionsMonitorSubmission: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 21, 18, 4, 31, "submissions.monitorSubmission")

    val submissionIdParam: Parameter = Parameter("submissionId", SilkStringType, isRequired = true)

    override def parameters: Seq[Parameter] =
      Seq(CommonParameters.workspaceNamespace, CommonParameters.workspaceName, submissionIdParam)

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      val workspaceNamespace = env.getString(CommonParameters.workspaceNamespace)
      val workspaceName = env.getString(CommonParameters.workspaceName)
      val submissionId = env.getString(submissionIdParam)
      val request = SubmissionsApi.monitorSubmission(workspaceNamespace, workspaceName, submissionId)
      SilkHttpUtils.sendAuthorizedPrintResponseReturnEmpty(request, env)
    }
  }

  val workspacesGetWorkspace: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 25, 17, 3, 57, "workspaces.getWorkspace")

    override def parameters: Seq[Parameter] = Seq(CommonParameters.workspaceNamespace, CommonParameters.workspaceName)

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      val workspaceNamespace = env.getString(CommonParameters.workspaceNamespace)
      val workspaceName = env.getString(CommonParameters.workspaceName)
      val request = WorkspacesApi.getWorkspace(workspaceNamespace, workspaceName)
      SilkHttpUtils.sendAuthorizedExtractData(request, env) { workspaceResponse =>
        val bucketName = workspaceResponse.workspace.bucketName
        SilkObjectValue(CommonIds.workspaceBucket -> SilkStringValue(bucketName))
      }
    }
  }

  val workspacesReadBucket: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 25, 16, 54, 36, "workspaces.readBucket")

    override def parameters: Seq[Parameter] = Seq(CommonParameters.workspaceNamespace, CommonParameters.workspaceName)

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      val workspaceNamespace = env.getString(CommonParameters.workspaceNamespace)
      val workspaceName = env.getString(CommonParameters.workspaceName)
      val request = WorkspacesApi.readBucket(workspaceNamespace, workspaceName)
      SilkHttpUtils.sendAuthorizedPrintResponseReturnEmpty(request, env)
    }
  }

  val all: Set[SilkCommand] =
    Set(
      statusStatus, helloWorld, silkUtilGetTime, set, silkDebugDump, profileSetProfile, entitiesGetEntitiesWithType,
      methodConfigurationsListWorkspaceMethodConfigs, submissionsCreateSubmission, submissionsMonitorSubmission,
      workspacesGetWorkspace, workspacesReadBucket
    )
  val allByRef: Map[SilkCommand.Ref, SilkCommand] = all.map(command => (command.ref, command)).toMap

  def get(ref: SilkCommand.Ref): Option[SilkCommand] = allByRef.get(ref)

}
