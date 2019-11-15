package org.broadinstitute.starfire.app.silk

import better.files.File
import sttp.client.{HttpURLConnectionBackend, Identity, NothingT, SttpBackend}

object SilkConfig {

  implicit val sttpBackend: SttpBackend[Identity, Nothing, NothingT] = HttpURLConnectionBackend()

  val configFile: File = File(System.getProperty("user.home"))
}
