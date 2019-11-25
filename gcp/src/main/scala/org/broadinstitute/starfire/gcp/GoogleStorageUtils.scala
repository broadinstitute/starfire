package org.broadinstitute.starfire.gcp

import com.google.auth.Credentials
import com.google.cloud.storage.StorageOptions

object GoogleStorageUtils {

  def readBucket(bucketName: String, credentials: Credentials): String = {
    val storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService
    storage.list(bucketName).toString
  }

}
