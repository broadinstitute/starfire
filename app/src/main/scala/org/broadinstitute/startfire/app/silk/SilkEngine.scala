package org.broadinstitute.startfire.app.silk

import org.broadinstitute.startfire.app.silk.Argument.{NamedArgument, PositionalArgument}
import org.broadinstitute.startfire.app.silk.SilkCommand.Parameter
import org.broadinstitute.startfire.app.silk.SilkType.SilkAny
import org.broadinstitute.startfire.app.silk.SilkValue.{SilkCommandValue, SilkObjectValue}
import org.broadinstitute.startfire.app.silk.commands.PredefCommands

object SilkEngine {

  case class ParameterArgumentPair(parameter: Parameter, argument: Argument)

  case class ArgumentMapping(id: Identifier, value: SilkValue)

  def evaluateArgument(expression: Expression,
                       env: SilkObjectValue,
                       requiredType: SilkType): Either[Error, SilkValue] = {
    val errorOrValue = expression match {
      case literal: SilkLiteral[_] => Right(literal.asValue)
      case identifier: Identifier =>
        env.get(identifier) match {
          case Some(value) => Right(value)
          case None => Left(Error(s"$identifier is not defined"))
        }
    }
    errorOrValue match {
      case Left(error) => Left(error)
      case Right(value) =>
        val valueType = value.silkType
        if(valueType.canBeUsedAs(requiredType)) {
          Right(value)
        } else {
          Left(Error(s"Value of type ${value.silkType} found, but ${requiredType} required."))
        }
    }
  }

  def createPositionalArgumentMappings(parameters: Seq[SilkCommand.Parameter],
                                       arguments: Seq[PositionalArgument],
                                       env: SilkObjectValue): Either[Error, Seq[ArgumentMapping]] = {
    var errorOpt: Option[Error] = None
    var index: Int = 0
    var mappings: Seq[ArgumentMapping] = Seq.empty
    while(errorOpt.isEmpty && index < parameters.size && index < arguments.size) {
      val parameter = parameters(index)
      val argument = arguments(index)
      evaluateArgument(argument.expression, env, parameter.silkType) match {
        case Left(error) => errorOpt = Some(error)
        case Right(value) => mappings :+= ArgumentMapping(parameter.id, value)
      }
      index += 1
    }
    errorOpt match {
      case Some(error) => Left(error)
      case None => Right(mappings)
    }
  }

  def createNamedArgumentMappings(parameters: Seq[SilkCommand.Parameter],
                                  namedArguments: Seq[NamedArgument]): Either[Error, Seq[ArgumentMapping]] = {
    ???
  }

  def createArgumentMappings(parameters: Seq[SilkCommand.Parameter],
                             positionalArguments: Seq[PositionalArgument],
                             namedArguments: Seq[NamedArgument]): Either[Error, Seq[ArgumentMapping]] = {
    ???
  }

  def pairUpPositionalArguments(parameters: Seq[SilkCommand.Parameter],
                                positionalArguments: Seq[PositionalArgument]): Seq[ParameterArgumentPair] = {
    positionalArguments.zipWithIndex.map {
      case (argument, index) =>
        val parameter =
          if(index < parameters.size) {
            parameters(index)
          } else {
            Parameter(Identifier("arg" + index), SilkAny, isRequired = false)
          }
        ParameterArgumentPair(parameter, argument)
    }
  }

  def pairUpNamedArguments(parameters: Seq[SilkCommand.Parameter],
                           namedArguments: Seq[NamedArgument]): Seq[ParameterArgumentPair] = {
    val parametersById = parameters.map(parameter => (parameter.id, parameter)).toMap
    namedArguments.map { argument =>
      val parameter = parametersById.getOrElse(argument.id, Parameter(argument.id, SilkAny, isRequired = false))
      ParameterArgumentPair(parameter, argument)
    }
  }

  def run(statement: Statement, env: SilkObjectValue): Either[Error, SilkObjectValue] = {
    val commandId = statement.identifier
    env.get(commandId) match {
      case None => Left(Error(s"Cannot find command with id $commandId"))
      case Some(commandValue: SilkCommandValue) =>
        PredefCommands.get(commandValue.ref) match {
          case None => Left(Error("Command not found."))
          case Some(command) =>
            val parameters = command.parameters
            for ((arg, index) <- statement.positionalArguments.zipWithIndex) {
              if (index < parameters.size) {
                val parameter = parameters(index)
              }
            }
            ???
        }
      case Some(value) => Left(Error(s"$commandId needs to refer to command, but I have $value"))
    }
  }

}
