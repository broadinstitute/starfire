package org.broadinstitute.starfire.app

import org.broadinstitute.starfire.app.silk.predef.PredefEnv
import org.broadinstitute.starfire.app.silk.{Parser, SilkEngine}

object Starfire {

  def main(args: Array[String]): Unit = {
    val commandLine = args.mkString(" ")
    Parser.parseCommandLine(commandLine) match {
      case Left(error) => println(error.allMessages)
      case Right(statement) =>
        println(statement.asSilkCode)
        val env = PredefEnv.env
        SilkEngine.run(statement, env) match {
          case Left(error) => println("Error: " + error.message)
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
        }
    }
  }

}
