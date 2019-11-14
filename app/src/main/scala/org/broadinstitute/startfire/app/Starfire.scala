package org.broadinstitute.startfire.app

import better.files.File
import org.broadinstitute.starfire.api.StatusApi
import org.broadinstitute.startfire.app.silk.predef.PredefEnv
import org.broadinstitute.startfire.app.silk.{Parser, SilkEngine}
import sttp.client.{HttpURLConnectionBackend, Identity, NothingT, SttpBackend}

object Starfire {

  def queryStatus(): Unit = {
    implicit val backend: SttpBackend[Identity, Nothing, NothingT] = HttpURLConnectionBackend()
    val request = StatusApi.status()
    val response = request.send()
    response.body match {
      case Left(error) => println(error.getMessage)
      case Right(status) =>
        println(s"It is OK: ${status.ok}")
        for((key, value) <- status.systems) {
          println(s"$key: ${value.toString()}")
        }
    }
  }

  def main(args: Array[String]): Unit = {
    val commandLine = args.mkString(" ")
    Parser.parseCommandLine(commandLine) match {
      case Left(error) => println(error.allMessages)
      case Right(statement) =>
        println(statement.asSilkCode)
        val env = PredefEnv.env
        SilkEngine.run(statement, env) match {
          case Left(error) => println("Error: " + error.message)
          case Right(value) =>
            println("Success!")
            println(value)
        }
    }
  }

}
