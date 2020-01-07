package org.broadinstitute.starfire.app.silk.predef

import better.files.File
import com.google.auth.oauth2.ServiceAccountCredentials
import org.broadinstitute.starfire.app.silk.SilkConfig
import org.broadinstitute.starfire.app.silk.SilkValue.SilkObjectValue
import org.broadinstitute.yootilz.core.snag.Snag
import org.broadinstitute.yootilz.gcp.auth.OAuthUtils

import scala.util.{Failure, Success}

object CommandUtils {

  def getKeyFile(env: SilkObjectValue): File =
    SilkConfig.silkDirectory / env.getString(CommonParameters.accountKeyFile.id).get

  def doAuthenticated[T](env: SilkObjectValue)(
    doer: ServiceAccountCredentials => Either[Snag,T]): Either[Snag, T] = {
    val keyFile = CommandUtils.getKeyFile(env)
    OAuthUtils.readServiceAccountCredentials(keyFile) match {
      case Failure(exception) => Left(Snag("Could not read key file", Snag(exception)))
      case Success(credentials) => doer(credentials)
    }
  }

}
