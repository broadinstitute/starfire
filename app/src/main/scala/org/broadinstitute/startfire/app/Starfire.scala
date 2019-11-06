package org.broadinstitute.startfire.app

import better.files.File
import org.broadinstitute.starfire.api.StatusApi
import sttp.client.HttpURLConnectionBackend

object Starfire {

  def main(args: Array[String]): Unit = {
    val commandLine = args.mkString(" ")
    println(commandLine)
    implicit val backend = HttpURLConnectionBackend()
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

}
