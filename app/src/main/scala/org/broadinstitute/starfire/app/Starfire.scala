package org.broadinstitute.starfire.app

import org.broadinstitute.starfire.app.silk.predef.PredefEnv
import org.broadinstitute.starfire.app.silk.{Parser, SilkConfig, SilkEngine, SilkValue, SilkError}

object Starfire {

  def main(args: Array[String]): Unit = {
    val commandLines = SilkConfig.configFileLines :+ args.mkString(" ")
    val env = PredefEnv.env
    SilkEngine.run(commandLines, env) match {
      case Left(error) => println(error.allMessages)
      case Right(outputValues) =>
        println("Success!")
        if(outputValues.isEmpty) {
          println("[no output]")
        } else {
          for(entry <- outputValues.entries) {
            println(entry.asReadableString)
          }
          println()
        }
        SilkConfig.writeConfigFile(env ++ outputValues)
    }
  }

}
