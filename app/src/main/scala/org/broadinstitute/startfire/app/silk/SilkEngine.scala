package org.broadinstitute.startfire.app.silk

import org.broadinstitute.startfire.app.silk.SilkValue.{SilkCommandValue, SilkObjectValue}
import org.broadinstitute.startfire.app.silk.commands.PredefCommands

object SilkEngine {

  def run(statement: Statement, env: SilkObjectValue): Either[Error, SilkObjectValue] = {
    val commandId = statement.identifier
    env.get(commandId) match {
      case None => Left(Error(s"Cannot find command with id $commandId"))
      case Some(commandValue: SilkCommandValue) =>
        PredefCommands.get(commandValue.ref) match {
          case None => Left(Error("Command not found."))
          case Some(command) =>
            command.parameters
            ???
        }
      case Some(value) => Left(Error(s"$commandId needs to refer to command, but I have $value"))
    }
  }

}
