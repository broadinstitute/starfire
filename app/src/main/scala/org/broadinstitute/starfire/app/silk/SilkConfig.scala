package org.broadinstitute.starfire.app.silk

import sttp.client.{HttpURLConnectionBackend, Identity, NothingT, SttpBackend}

object SilkConfig {

  implicit val sttpBackend: SttpBackend[Identity, Nothing, NothingT] = HttpURLConnectionBackend()

}
