package org.broadinstitute.starfire.utils

object StringToLongHash {

  def hash(string: String): Long = {
    var hash: Long = 0L
    val factor: Long = 31L
    var remainder: String = string
    while(remainder.nonEmpty) {
      hash = factor*hash + remainder.head
      remainder = remainder.tail
    }
    hash
  }

}
