package org.broadinstitute.starfire.gcp

import com.google.api.gax.paging.Page
import com.google.auth.Credentials
import com.google.cloud.storage.{Blob, StorageOptions}

import scala.jdk.CollectionConverters.IteratorHasAsScala

object GoogleStorageUtils {

  def readBucket(bucketName: String, credentials: Credentials): Iterator[Blob] = {
    val storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService
    val page = storage.list(bucketName)
    page.iterateAll().iterator().asScala
  }

  def getPageIterator[T](page: Page[T]): Iterator[Page[T]] = new PageIterator[T](page)

  def getItemIterator[T](page: Page[T]): Iterator[T] = getPageIterator(page).flatMap(_.getValues.iterator().asScala)

  private class PageIterator[T](page: Page[T]) extends Iterator[Page[T]] {
    private var pageInternal = page
    override def hasNext: Boolean = pageInternal.hasNextPage
    override def next(): Page[T] = {
      pageInternal = pageInternal.getNextPage
      pageInternal
    }
  }

}
