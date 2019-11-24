package org.broadinstitute.starfire.utils

import io.circe.ParsingFailure
import io.circe.parser.parse
import org.broadinstitute.starfire.util.Snag

object JsonUtils {
  def prettyPrintJson(original: String): Either[Snag, String] = {
    parse(original) match {
      case Left(ParsingFailure(message, underlying))  => Left(Snag(message, Snag(underlying)))
      case Right(json) => Right(json.spaces2)
    }
  }

}
