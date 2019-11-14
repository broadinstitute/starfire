package org.broadinstitute.startfire.app.silk

import org.broadinstitute.startfire.app.silk.Argument.{NamedArgument, PositionalArgument}
import org.broadinstitute.startfire.app.silk.SilkCommand.Parameter
import org.broadinstitute.startfire.app.silk.SilkType.SilkAny
import org.broadinstitute.startfire.app.silk.SilkValue.{SilkCommandValue, SilkObjectValue}
import org.broadinstitute.startfire.app.silk.commands.PredefCommands

object SilkEngine {

  case class ParameterArgumentPair(parameter: Parameter, argument: Argument)

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

  def mapPositionalArguments(parameters: Seq[SilkCommand.Parameter],
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

  def mapNamedArguments(parameters: Seq[SilkCommand.Parameter],
                        namedArguments: Seq[NamedArgument]): Seq[ParameterArgumentPair] = {
    val parametersById = parameters.map(parameter => (parameter.id, parameter)).toMap
    namedArguments.map { argument =>
      val parameter = parametersById.getOrElse(argument.id, Parameter(argument.id, SilkAny, isRequired = false))
      ParameterArgumentPair(parameter, argument)
    }
  }

  def errorIfDuplicateParameter(parameterArgumentPairs: Seq[ParameterArgumentPair]): Option[Error] = {
    parameterArgumentPairs.groupBy(_.parameter.id).collectFirst {
      case (id, pairs) if pairs.size > 1 => (id, pairs)
    }.map {
      case (id, pairs) =>
        val nPositionalArg = pairs.map(_.argument).count(_.isInstanceOf[PositionalArgument])
        val nNamedArg = pairs.map(_.argument).count(_.isInstanceOf[NamedArgument])
        val argsText = (nPositionalArg, nNamedArg) match {
          case (2, 0) => "two positional arguments"
          case (1,1) => "one positional and one named argument"
          case (0, 2) => "two named arguments"
          case (_, 0) => "multiple positional arguments"
          case (0, _) => "multiple named arguments"
          case (_, _) => "multiple arguments"
        }
        Error(s"Duplicated parameter $id has been used for $argsText.")
    }
  }

  def mapArguments(parameters: Seq[SilkCommand.Parameter],
                   positionalArguments: Seq[PositionalArgument],
                   namedArguments: Seq[NamedArgument]): Either[Error, Seq[ParameterArgumentPair]] = {
    ???
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

            ???
        }
      case Some(value) => Left(Error(s"$commandId needs to refer to command, but I have $value"))
    }
  }

}
