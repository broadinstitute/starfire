package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.app.silk.SilkCommand.Parameter
import org.broadinstitute.starfire.app.silk.SilkType.SilkStringType

object CommonParameters {
  val accountEmail: Parameter = Parameter(CommonIds.starfireAccountEmail, SilkStringType, isRequired = true)
  val accountKeyFile: Parameter = Parameter(CommonIds.starfireAccountKeyFile, SilkStringType, isRequired = true)
  val workspaceNamespace: Parameter = Parameter(CommonIds.workspaceNamespace, SilkStringType, isRequired = true)
  val workspaceName: Parameter = Parameter(CommonIds.workspaceName, SilkStringType, isRequired = true)
  val workspaceBucket: Parameter = Parameter(CommonIds.workspaceBucket, SilkStringType, isRequired = true)
  val sourceBucket: Parameter = Parameter(CommonIds.sourceBucket, SilkStringType, isRequired = true)
  val sourceName: Parameter = Parameter(CommonIds.sourceName, SilkStringType, isRequired = true)
  val targetBucket: Parameter = Parameter(CommonIds.targetBucket, SilkStringType, isRequired = true)
  val targetName: Parameter = Parameter(CommonIds.targetName, SilkStringType, isRequired = true)
  val projectIdOptional: Parameter = Parameter(CommonIds.projectId, SilkStringType, isRequired = false)
  val projectId: Parameter = Parameter(CommonIds.projectId, SilkStringType, isRequired = true)
}
