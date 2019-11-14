package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.app.silk.Identifier
import org.broadinstitute.starfire.app.silk.SilkValue.{SilkCommandValue, SilkObjectValue}

object PredefEnv {

  val theEnv: SilkObjectValue =
    SilkObjectValue.empty.add(Identifier("status") /  "status", SilkCommandValue(PredefCommands.statusStatus.ref))

  def env: SilkObjectValue = theEnv

}
