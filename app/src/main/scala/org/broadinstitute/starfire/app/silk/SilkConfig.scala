package org.broadinstitute.starfire.app.silk

import better.files.File
import org.broadinstitute.starfire.app.silk.SilkValue.SilkObjectValue
import sttp.client.{HttpURLConnectionBackend, Identity, NothingT, SttpBackend}

object SilkConfig {

  implicit val sttpBackend: SttpBackend[Identity, Nothing, NothingT] = HttpURLConnectionBackend()

  val silkDirectory: File = File(System.getProperty("user.home")) / ".silk"

  val configFile: File = silkDirectory / "silkrc"

  def writeConfigFile(env: SilkObjectValue): Unit = {
    if(!silkDirectory.exists) {
      silkDirectory.createDirectory
    }
    configFile.clear()
    for(entry <- env.entries) {
      configFile.append(entry.asSetStatement + "\n")
    }
  }

  def configFileLines: Seq[String] = configFile.lines.toSeq
}
