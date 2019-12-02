package org.broadinstitute.starfire.gcp

import java.nio.channels.FileChannel

import better.files.File
import com.google.api.gax.paging.Page
import com.google.auth.Credentials
import com.google.cloud.storage.Storage.{BlobListOption, CopyRequest}
import com.google.cloud.storage.{Blob, BlobId, BlobInfo, Storage, StorageOptions}

import scala.jdk.CollectionConverters.IteratorHasAsScala

class GoogleStorageUtils(credentials: Credentials, projectIdOpt: Option[String] = None) {
  val storage: Storage = {
    var builder = StorageOptions.newBuilder().setCredentials(credentials)
    projectIdOpt.foreach(projectId => builder = builder.setProjectId(projectId))
    println("builder=" + builder)
    builder.build().getService
  }

  def listBucket(bucketName: String, prefixOpt: Option[String] = None): Iterator[Blob] = {
    val blobListOptions: Seq[BlobListOption] =
      prefixOpt.map(BlobListOption.prefix).toSeq ++ projectIdOpt.map(Storage.BlobListOption.userProject).toSeq
    val page = storage.list(bucketName, blobListOptions: _*)
    page.iterateAll().iterator().asScala
  }

  def copyFile(source: BlobId, target: BlobId): Unit = {
    var builder: CopyRequest.Builder = new Storage.CopyRequest.Builder().setSource(source).setTarget(target)
    projectIdOpt.foreach(projectId => {
      val option = Storage.BlobSourceOption.userProject(projectId)
      builder = builder.setSourceOptions(option)
    })
    val copyRequest = builder.build()
    storage.copy(copyRequest)
  }

  def copyFiles(sourceBucket: String, sourcePrefix: String,
                targetBucket: String, targetPrefix: String): Seq[String] = {
    val blobIter = listBucket(sourceBucket, Some(sourcePrefix))
    var filesCopied: Seq[String] = Seq.empty
    val sourcePrefixSize = sourcePrefix.size
    for (sourceBlob <- blobIter) {
      val sourceFileName = sourceBlob.getBlobId.getName
      val targetFileName = targetPrefix + sourceFileName.substring(sourcePrefixSize)
      filesCopied :+= targetFileName
      val targetBlobId = BlobId.of(targetBucket, targetFileName)
      copyFile(sourceBlob.getBlobId, targetBlobId)
    }
    filesCopied
  }

  val maxFileReadSize = 1000000

  def uploadFile(file: File, blobInfo: BlobInfo): Unit = {
    val writer = storage.writer(blobInfo)
    val fileChannel = file.fileChannel.get()
    var pos: Long = 0L
    val fileSize = fileChannel.size()
    while (pos < fileSize) {
      val remaining = fileSize - pos
      val readSize = if (remaining < maxFileReadSize) remaining else maxFileReadSize
      val buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, pos, readSize)
      writer.write(buffer)
      pos += readSize
    }
  }

  def downloadFile(blobId: BlobId, file: File): Unit = {
    val blob = storage.get(blobId)
    blob.downloadTo(file.path)
  }
}

object GoogleStorageUtils {
  def apply(credentials: Credentials): GoogleStorageUtils = new GoogleStorageUtils(credentials)

  def apply(credentials: Credentials, projectId: String): GoogleStorageUtils =
    new GoogleStorageUtils(credentials, Some(projectId))

  def getPageIterator[T](page: Page[T]): Iterator[Page[T]] = new PageIterator[T](page)

  def getItemIterator[T](page: Page[T]): Iterator[T] = getPageIterator(page).flatMap(_.getValues.iterator().asScala)

  private class PageIterator[T](page: Page[T]) extends Iterator[Page[T]] {
    private var currentPage = page

    override def hasNext: Boolean = currentPage.hasNextPage

    override def next(): Page[T] = {
      currentPage = currentPage.getNextPage
      currentPage
    }
  }

}
