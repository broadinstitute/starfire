package org.broadinstitute.starfire.app.silk

import org.broadinstitute.starfire.app.silk.Argument.{NamedArgument, PositionalArgument}
import org.broadinstitute.starfire.app.silk.SilkCommand.Parameter
import org.broadinstitute.starfire.app.silk.SilkType.SilkAny
import org.broadinstitute.starfire.app.silk.SilkValue.{SilkCommandValue, SilkObjectValue}
import org.broadinstitute.starfire.app.silk.predef.PredefCommands

object SilkEngine {

  case class ParameterArgumentPair(parameter: Parameter, argument: Argument)

  def evaluateArgument(parameterArgumentPair: ParameterArgumentPair,
                       env: SilkObjectValue): Either[Error, SilkValue] = {
    val errorOrValue = parameterArgumentPair.argument.expression match {
      case literal: SilkLiteral => Right(literal.asValue)
      case identifier: Identifier =>
        env.get(identifier) match {
          case Some(value) => Right(value)
          case None => Left(Error(s"$identifier is not defined"))
        }
    }
    errorOrValue.flatMap { value =>
      val requiredType = parameterArgumentPair.parameter.silkType
      val valueType = value.silkType
      if (valueType.canBeUsedAs(requiredType)) {
        Right(value)
      } else Left(Error(s"Value of type ${value.silkType} found, but $requiredType required."))
    }
  }

  def evaluateArguments(parameterArgumentPairs: Seq[ParameterArgumentPair],
                        env: SilkObjectValue): Either[Error, SilkObjectValue] = {
    var envWithArgs: SilkObjectValue = env
    val paramArgPairIter = parameterArgumentPairs.iterator
    var errorOpt: Option[Error] = None
    while(errorOpt.isEmpty && paramArgPairIter.hasNext) {
      val paramArgPair = paramArgPairIter.next()
      evaluateArgument(paramArgPair, envWithArgs) match {
        case Left(error) => errorOpt = Some(error)
        case Right(value) =>
          envWithArgs = envWithArgs.add(paramArgPair.parameter.id, value)
      }
    }
    errorOpt match {
      case Some(error) => Left(error)
      case None => Right(envWithArgs)
    }
  }

  def mapPositionalArguments(parameters: Seq[SilkCommand.Parameter],
                             positionalArguments: Seq[PositionalArgument]): Seq[ParameterArgumentPair] = {
    positionalArguments.zipWithIndex.map {
      case (argument, index) =>
        val parameter =
          if (index < parameters.size) {
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

  def errorIfDuplicateParameter(parameterArgumentPairs: Seq[ParameterArgumentPair],
                                argsSummary: String): Option[Error] = {
    parameterArgumentPairs.groupBy(_.parameter.id).collectFirst {
      case (id, pairs) if pairs.size > 1 => (id, pairs)
    }.map {
      case (id, _) =>
        Error(s"Duplicated parameter ${id.asSilkCode}. It has been used for $argsSummary.")
    }
  }

  def errorIfRequiredParamMissing(parameters: Seq[Parameter],
                                  parameterArgumentPairs: Seq[ParameterArgumentPair]): Option[Error] = {
    val requiredIds = parameters.filter(_.isRequired).map(_.id).toSet
    val givenIds = parameterArgumentPairs.map(_.parameter.id).toSet
    val missingIds = requiredIds -- givenIds
    missingIds.headOption.map(id => Error(s"Missing required parameter ${id.asSilkCode}."))
  }

  def mapArguments(parameters: Seq[SilkCommand.Parameter],
                   positionalArguments: Seq[PositionalArgument],
                   namedArguments: Seq[NamedArgument]): Either[Error, Seq[ParameterArgumentPair]] = {
    val posParArgs = mapPositionalArguments(parameters, positionalArguments)
    errorIfDuplicateParameter(posParArgs, "multiple positional arguments") match {
      case Some(error) => Left(error)
      case None =>
        val namedParArgs = mapNamedArguments(parameters, namedArguments)
        errorIfDuplicateParameter(namedParArgs, "multiple named arguments") match {
          case Some(error) => Left(error)
          case None =>
            val parArgs = posParArgs ++ namedParArgs
            errorIfDuplicateParameter(parArgs, "a positional and a named argument") match {
              case Some(error) => Left(error)
              case None =>
                errorIfRequiredParamMissing(parameters, parArgs) match {
                  case Some(error) => Left(error)
                  case None => Right(parArgs)
                }
            }
        }
    }
  }

  def run(statement: Statement, env: SilkObjectValue): Either[Error, SilkObjectValue] = {
    val commandId = statement.identifier
    env.get(commandId) match {
      case None => Left(Error(s"Cannot find command with id ${commandId.asSilkCode}"))
      case Some(commandValue: SilkCommandValue) =>
        PredefCommands.get(commandValue.ref) match {
          case None => Left(Error("Command not found."))
          case Some(command) =>
            val parameters = command.parameters
            mapArguments(parameters, statement.positionalArguments, statement.namedArguments) match {
              case Left(error) => Left(error)
              case Right(paramArgPairs) =>
                evaluateArguments(paramArgPairs, env) match {
                  case Left(error) => Left(error)
                  case Right(envWithArgs) => command.execute(envWithArgs)
                }
            }
        }
      case Some(value) => Left(Error(s"$commandId needs to refer to command, but I have $value"))
    }
  }

}
