package org.broadinstitute.starfire.app.silk.predef

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
    }

  }

}
