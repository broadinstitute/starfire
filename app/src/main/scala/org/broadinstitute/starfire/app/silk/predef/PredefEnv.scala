package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.app.silk.Identifier
import org.broadinstitute.starfire.app.silk.SilkValue.SilkObjectValue
import org.broadinstitute.starfire.app.silk.predef.PredefUtils.Implicits._


object PredefEnv {

  val theEnv: SilkObjectValue =
    SilkObjectValue.empty
      .add("hello" / "world", PredefCommands.helloWorld)
      .add("status" / "status", PredefCommands.statusStatus)
      .add("silk" / "util" / "getTime", PredefCommands.silkUtilGetTime)
      .add(Identifier("set"), PredefCommands.set)
      .add("silk" / "debug" / "dump", PredefCommands.silkDebugDump)
      .add("profile" / "setProfile", PredefCommands.profileSetProfile)
      .add("entities" / "getEntitiesWithType", PredefCommands.entitiesGetEntitiesWithType)
      .add("methodConfigurations" / "listWorkspaceMethodConfigs",
        PredefCommands.methodConfigurationsListWorkspaceMethodConfigs)
      .add("submissions" / "createSubmission", PredefCommands.submissionsCreateSubmission)
      .add("submissions" / "monitorSubmission", PredefCommands.submissionsMonitorSubmission)
      .add("workspaces" / "getWorkspace", PredefCommands.workspacesGetWorkspace)
      .add("workspaces" / "readBucket", PredefCommands.workspacesReadBucket)
      .add("gcp" / "listBucket", PredefCommands.gcpListBucket)
      .add("gcp" / "copy", PredefCommands.gcpCopy)
      .add("gcp" / "bulkCopy", PredefCommands.gcpBulkCopy)
      .add("gcp" / "upload", PredefCommands.gcpUpload)
      .add("gcp" / "download", PredefCommands.gcpDownload)
      .add(Identifier("help"), PredefCommands.help)

  def env: SilkObjectValue = theEnv

}
