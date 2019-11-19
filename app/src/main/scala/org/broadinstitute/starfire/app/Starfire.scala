package org.broadinstitute.starfire.app

import org.broadinstitute.starfire.app.silk.predef.PredefEnv
import org.broadinstitute.starfire.app.silk.{SilkConfig, SilkEngine}

object Starfire {

  def main(args: Array[String]): Unit = {
    val commandLines = SilkConfig.configFileLines :+ args.mkString(" ")
    val env = PredefEnv.env
    SilkEngine.run(commandLines, env) match {
      case Left(error) =>
        println("Error!")
        println(error.report)
      case Right(outputValues) =>
        println("Success!")
        SilkConfig.writeConfigFile(env ++ outputValues)
    }
  }

}
