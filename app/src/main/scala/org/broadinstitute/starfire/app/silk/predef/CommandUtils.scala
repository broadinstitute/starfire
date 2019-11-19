package org.broadinstitute.starfire.app.silk.predef

import better.files.File
import org.broadinstitute.starfire.app.silk.SilkConfig
import org.broadinstitute.starfire.app.silk.SilkValue.SilkObjectValue

object CommandUtils {

  def getKeyFile(env: SilkObjectValue): File =
    SilkConfig.silkDirectory / env.getString(CommonParameters.accountKeyFile.id).get

}
