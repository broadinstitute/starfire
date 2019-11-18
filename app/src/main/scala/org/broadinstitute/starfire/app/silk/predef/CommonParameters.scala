package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.app.silk.SilkCommand.Parameter
import org.broadinstitute.starfire.app.silk.SilkType.SilkStringType

object CommonParameters {

  val accountEmail: Parameter = Parameter(CommonIds.starfireAccountEmail, SilkStringType, isRequired = true)
  val accountKeyFile: Parameter = Parameter(CommonIds.starfireAccountKeyFile, SilkStringType, isRequired = true)

}
