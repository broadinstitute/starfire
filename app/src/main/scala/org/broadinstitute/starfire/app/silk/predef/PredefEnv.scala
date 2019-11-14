package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.app.silk.SilkValue.SilkObjectValue
import org.broadinstitute.starfire.app.silk.predef.PredefUtils.Implicits._


object PredefEnv {

  val theEnv: SilkObjectValue =
    SilkObjectValue.empty
      .add("hello" / "world", PredefCommands.helloWorld)
      .add("status" / "status", PredefCommands.statusStatus)

  def env: SilkObjectValue = theEnv

}
