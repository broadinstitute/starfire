package org.broadinstitute.starfire.app.silk.predef

import better.files.File
import com.google.cloud.storage.{BlobId, BlobInfo}
import org.broadinstitute.starfire.api.{EntitiesApi, MethodConfigurationsApi, ProfileApi, StatusApi, SubmissionsApi, WorkspacesApi}
import org.broadinstitute.starfire.app.silk.SilkCommand.Parameter
import org.broadinstitute.starfire.app.silk.SilkConfig.sttpBackend
import org.broadinstitute.starfire.app.silk.SilkType.SilkStringType
import org.broadinstitute.starfire.app.silk.SilkValue.{SilkCommandValue, SilkIntegerValue, SilkObjectValue, SilkStringValue}
import org.broadinstitute.starfire.app.silk.predef.PredefUtils.Implicits._
import org.broadinstitute.starfire.app.silk.{Identifier, SilkCommand, SilkHttpUtils}
import org.broadinstitute.starfire.auth.OAuthUtils
import org.broadinstitute.starfire.model.{Profile, SubmissionRequest}
import org.broadinstitute.starfire.util.Snag
import org.broadinstitute.starfire.utils.HttpUtils
import org.joda.time.DateTime
import org.broadinstitute.starfire.gcp.GoogleStorageUtils

import scala.util.{Failure, Success}

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
      val dateTimeString = dateTimeNow.toString()
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
      for (entry <- env.entries) {
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

  val gcpListBucket: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 25, 18, 28, 12, "gcp.readBucket")

    val prefixParam: Parameter = Parameter("gcp" / "prefix", SilkStringType, isRequired = false)

    override def parameters: Seq[Parameter] =
      Seq(CommonParameters.accountKeyFile, CommonParameters.workspaceBucket, prefixParam)

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      CommandUtils.doAuthenticated(env) { credentials =>
        val bucketName = env.getString(CommonParameters.workspaceBucket)
        val prefixOpt = env.getStringOpt(prefixParam)
        val blobIter = GoogleStorageUtils(credentials).listBucket(bucketName, prefixOpt)
        for (blob <- blobIter.take(100)) {
          println(blob.getName)
        }
        Right(SilkObjectValue.empty)
      }
    }
  }

  val gcpCopy: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 27, 18, 19, 25, "gcp.copy")

    override def parameters: Seq[Parameter] =
      Seq(
        CommonParameters.accountKeyFile, CommonParameters.sourceBucket, CommonParameters.sourceName,
        CommonParameters.targetBucket, CommonParameters.targetName
      )

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      CommandUtils.doAuthenticated(env) { credentials =>
        val sourceBucket = env.getString(CommonParameters.sourceBucket)
        val sourceName = env.getString(CommonParameters.sourceName)
        val targetBucket = env.getString(CommonParameters.targetBucket)
        val targetName = env.getString(CommonParameters.targetName)
        val sourceBlobId = BlobId.of(sourceBucket, sourceName)
        val targetBlobId = BlobId.of(targetBucket, targetName)
        GoogleStorageUtils(credentials).copyFile(sourceBlobId, targetBlobId)
        Right(SilkObjectValue.empty)
      }
    }
  }

  val gcpBulkCopy: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 27, 18, 40, 10, "gcp.bulkCopy")

    val sourcePrefixParam: Parameter = Parameter("sourcePrefix", SilkStringType, isRequired = true)
    val targetPrefixParam: Parameter = Parameter("targetPrefix", SilkStringType, isRequired = true)

    override def parameters: Seq[Parameter] =
      Seq(
        CommonParameters.accountKeyFile, CommonParameters.sourceBucket, sourcePrefixParam,
        CommonParameters.targetBucket, targetPrefixParam
      )

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      CommandUtils.doAuthenticated(env) { credentials =>
        val sourceBucket = env.getString(CommonParameters.sourceBucket)
        val sourcePrefix = env.getString(sourcePrefixParam)
        val targetBucket = env.getString(CommonParameters.targetBucket)
        val targetPrefix = env.getString(targetPrefixParam)
        val filesCopied =
          GoogleStorageUtils(credentials).copyFiles(sourceBucket, sourcePrefix, targetBucket, targetPrefix)
        for(fileCopied <- filesCopied) {
          println(s"Copied $filesCopied")
        }
        Right(SilkObjectValue.empty)
      }
    }
  }

  val gcpUpload: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 27, 18, 48, 13, "gcp.upload")

    val sourceFileParam: Parameter = Parameter("sourceFile", SilkStringType, isRequired = true)
    val contentTypeParam: Parameter = Parameter("contentType", SilkStringType, isRequired = true)

    override def parameters: Seq[Parameter] =
      Seq(
        CommonParameters.accountKeyFile, sourceFileParam, CommonParameters.targetBucket, CommonParameters.targetName,
        contentTypeParam
      )

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      CommandUtils.doAuthenticated(env) { credentials =>
        val sourceFile = File(env.getString(sourceFileParam))
        val targetBucket = env.getString(CommonParameters.targetBucket)
        val targetName = env.getString(CommonParameters.targetName)
        val contentType = env.getString(contentTypeParam)
        val targetBlobInfo =
          BlobInfo.newBuilder(BlobId.of(targetBucket, targetName)).setContentType(contentType).build()
        GoogleStorageUtils(credentials).uploadFile(sourceFile, targetBlobInfo)
        Right(SilkObjectValue.empty)
      }
    }
  }

  val gcpDownload: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 27, 18, 55, 50, "gcp.download")

    val targetFileParam: Parameter = Parameter("targetFile", SilkStringType, isRequired = true)

    override def parameters: Seq[Parameter] =
      Seq(
        CommonParameters.accountKeyFile, CommonParameters.sourceBucket, CommonParameters.sourceName, targetFileParam
      )

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      CommandUtils.doAuthenticated(env) { credentials =>
        val sourceBucket = env.getString(CommonParameters.sourceBucket)
        val sourceName = env.getString(CommonParameters.sourceName)
        val targetFile = File(env.getString(targetFileParam))
        GoogleStorageUtils(credentials).downloadFile(BlobId.of(sourceBucket, sourceName), targetFile)
        Right(SilkObjectValue.empty)
      }
    }
  }

  val help: SilkCommand = new SilkCommand {
    override def ref: SilkCommand.Ref = SilkCommand.Ref(2019, 11, 27, 16, 17, 32, "help")

    val commandParam: Parameter = Parameter("command", SilkStringType, isRequired = true)

    override def parameters: Seq[Parameter] = Seq(commandParam)

    override def execute(env: SilkObjectValue): Either[Snag, SilkObjectValue] = {
      Identifier.parse(env.getString(commandParam)).flatMap { identifier =>
        env.get(identifier) match {
          case None => Left(Snag(s"Could not find anything with id ${identifier.asSilkCode}"))
          case Some(SilkCommandValue(ref)) =>
            PredefCommands.allByRef.get(ref) match {
              case Some(command) =>
                for (param <- command.parameters) {
                  val isRequiredText = if (param.isRequired) "required" else "not required"
                  println(s"${param.id.asSilkCode} has type ${param.silkType.name} and is $isRequiredText.")
                }
                Right(SilkObjectValue.empty)
              case None => Left(Snag("Command not defined"))
            }
          case Some(value) => Left(Snag(s"Expected command, but got ${value.asReadableString}"))
        }
      }
    }
  }

  val all: Set[SilkCommand] =
    Set(
      statusStatus, helloWorld, silkUtilGetTime, set, silkDebugDump, profileSetProfile, entitiesGetEntitiesWithType,
      methodConfigurationsListWorkspaceMethodConfigs, submissionsCreateSubmission, submissionsMonitorSubmission,
      workspacesGetWorkspace, workspacesReadBucket, gcpListBucket, gcpCopy, gcpBulkCopy, gcpUpload, gcpDownload, help
    )
  val allByRef: Map[SilkCommand.Ref, SilkCommand] = all.map(command => (command.ref, command)).toMap

  def get(ref: SilkCommand.Ref): Option[SilkCommand] = allByRef.get(ref)

}
