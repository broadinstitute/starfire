package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.app.silk.Identifier
import org.broadinstitute.starfire.app.silk.predef.PredefUtils.Implicits.stringToIdentifier

object CommonIds {
  val starfireAccountEmail: Identifier = "starfire" / "account" / "email"
  val starfireAccountKeyFile: Identifier = "starfire" / "account" / "keyFile"
  val starfireAccount: Identifier = "starfire" / "account"
  val workspaceNamespace: Identifier = "workspaceNamespace"
  val workspaceName: Identifier = "workspaceName"
  val workspaceBucket: Identifier = "workspace" / "bucket"
  val sourceBucket: Identifier = "sourceBucket"
  val sourceName: Identifier = "sourceName"
  val targetBucket: Identifier = "targetBucket"
  val targetName: Identifier = "targetName"
  val projectId: Identifier = "projectId"

}
