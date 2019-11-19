package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.app.silk.Identifier
import org.broadinstitute.starfire.app.silk.predef.PredefUtils.Implicits.stringToIdentifier

object CommonIds {
  val starfireAccountEmail: Identifier = "starfire" / "account" / "email"
  val starfireAccountKeyFile: Identifier = "starfire" / "account" / "keyFile"
  val starfireAccount: Identifier = "starfire" / "account"
  val workspaceNamespace: Identifier = "workspaceNamespace"
  val workspaceName: Identifier = "workspaceName"

}
