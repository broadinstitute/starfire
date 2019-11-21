package org.broadinstitute.starfire.app.silk.predef

import org.broadinstitute.starfire.app.silk.SilkCommand.Parameter
import org.broadinstitute.starfire.app.silk.SilkValue.{SilkCommandValue, SilkObjectValue}
import org.broadinstitute.starfire.app.silk.{Identifier, SilkCommand}

import scala.language.implicitConversions

object PredefUtils {

  object Implicits {
    implicit def stringToIdentifier(string: String): Identifier = Identifier(string)

    implicit class RichEnv(val env: SilkObjectValue) extends AnyVal {
      def add(id: Identifier, command: SilkCommand): SilkObjectValue = {
        env.add(id, SilkCommandValue(command.ref))
      }
      def getStringOpt(parameter: Parameter): Option[String] = env.getString(parameter.id)
      def getString(parameter: Parameter): String = getStringOpt(parameter).get
    }

  }

}
