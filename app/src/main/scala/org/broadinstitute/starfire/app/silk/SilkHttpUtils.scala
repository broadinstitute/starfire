package org.broadinstitute.starfire.app.silk

import org.broadinstitute.starfire.app.silk.SilkValue.SilkObjectValue
import org.broadinstitute.starfire.app.silk.predef.CommandUtils
import org.broadinstitute.starfire.util.Snag
import org.broadinstitute.starfire.utils.HttpUtils
import sttp.client.{Identity, NothingT, Request, ResponseError, SttpBackend}

object SilkHttpUtils {

  val scopes: Seq[String] = Seq("openid", "email", "profile", "https://www.googleapis.com/auth/cloud-billing")

  def sendAuthorized[T](request: Request[Either[ResponseError[io.circe.Error], T], Nothing],
                        env: SilkObjectValue
                       )(implicit backend: SttpBackend[Identity, Nothing, NothingT]): Either[Snag, T] = {
    val keyFile = CommandUtils.getKeyFile(env)
    HttpUtils.sendAuthorized(request, keyFile, scopes)
  }

  def sendAuthorizedPrintResponseReturnEmpty[T](request: Request[Either[ResponseError[io.circe.Error], T], Nothing],
                                                env: SilkObjectValue
                                               )(implicit backend: SttpBackend[Identity, Nothing, NothingT]):
  Either[Snag, SilkObjectValue] = {
    sendAuthorized(request, env).map { value =>
      println(value)
      SilkObjectValue.empty
    }
  }

}
