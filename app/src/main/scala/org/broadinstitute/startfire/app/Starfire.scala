package org.broadinstitute.startfire.app

import better.files.File
import org.broadinstitute.starfire.api.StatusApi
import org.broadinstitute.startfire.app.silkie.Parser
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
    val commandLine = "status.status 1 2 3 4 \"x\" y=3 z=\"yo\""// args.mkString(" ")
    println(commandLine)
    Parser.parseCommandLine(commandLine) match {
      case Right(command) => println(command.asSilkieCode)
      case Left(error) => println(error.allMessages)
    }
  }

}
