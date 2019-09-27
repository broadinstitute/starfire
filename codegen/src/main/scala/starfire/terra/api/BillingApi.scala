/**
 * FireCloud
 * Genome analysis execution service. 
 *
 * OpenAPI spec version: 0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package starfire.terra.api

import java.text.SimpleDateFormat

import starfire.terra.model.BillingProjectMember
import starfire.terra.model.CreateRawlsBillingProjectFullRequest
import starfire.terra.model.ErrorReport
import io.swagger.client.{ApiInvoker, ApiException}

import com.sun.jersey.multipart.FormDataMultiPart
import com.sun.jersey.multipart.file.FileDataBodyPart

import javax.ws.rs.core.MediaType

import java.io.File
import java.util.Date
import java.util.TimeZone

import scala.collection.mutable.HashMap

import com.wordnik.swagger.client._
import scala.concurrent.Future
import collection.mutable

import java.net.URI

import com.wordnik.swagger.client.ClientResponseReaders.Json4sFormatsReader._
import com.wordnik.swagger.client.RequestWriters.Json4sFormatsWriter._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

import org.json4s._

class BillingApi(
  val defBasePath: String = "https://localhost",
  defApiInvoker: ApiInvoker = ApiInvoker
) {
  private lazy val dateTimeFormatter = {
    val formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"))
    formatter
  }
  private val dateFormatter = {
    val formatter = new SimpleDateFormat("yyyy-MM-dd")
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"))
    formatter
  }
  implicit val formats = new org.json4s.DefaultFormats {
    override def dateFormatter = dateTimeFormatter
  }
  implicit val stringReader: ClientResponseReader[String] = ClientResponseReaders.StringReader
  implicit val unitReader: ClientResponseReader[Unit] = ClientResponseReaders.UnitReader
  implicit val jvalueReader: ClientResponseReader[JValue] = ClientResponseReaders.JValueReader
  implicit val jsonReader: ClientResponseReader[Nothing] = JsonFormatsReader
  implicit val stringWriter: RequestWriter[String] = RequestWriters.StringWriter
  implicit val jsonWriter: RequestWriter[Nothing] = JsonFormatsWriter

  var basePath: String = defBasePath
  var apiInvoker: ApiInvoker = defApiInvoker

  def addHeader(key: String, value: String): mutable.HashMap[String, String] = {
    apiInvoker.defaultHeaders += key -> value
  }

  val config: SwaggerConfig = SwaggerConfig.forUrl(new URI(defBasePath))
  val client = new RestClient(config)
  val helper = new BillingApiAsyncHelper(client, config)

  /**
   * add user to billing project the caller owns
   * 
   *
   * @param projectId Project ID 
   * @param workbenchRole role of user for project 
   * @param email email of user or group to add 
   * @return void
   */
  def addUserToBillingProject(projectId: String, workbenchRole: String, email: String) = {
    val await = Try(Await.result(addUserToBillingProjectAsync(projectId, workbenchRole, email), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * add user to billing project the caller owns asynchronously
   * 
   *
   * @param projectId Project ID 
   * @param workbenchRole role of user for project 
   * @param email email of user or group to add 
   * @return Future(void)
   */
  def addUserToBillingProjectAsync(projectId: String, workbenchRole: String, email: String) = {
      helper.addUserToBillingProject(projectId, workbenchRole, email)
  }

  /**
   * create billing project in FireCloud and google
   * 
   *
   * @param createProjectRequest create project request 
   * @return void
   */
  def createBillingProjectFull(createProjectRequest: CreateRawlsBillingProjectFullRequest) = {
    val await = Try(Await.result(createBillingProjectFullAsync(createProjectRequest), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * create billing project in FireCloud and google asynchronously
   * 
   *
   * @param createProjectRequest create project request 
   * @return Future(void)
   */
  def createBillingProjectFullAsync(createProjectRequest: CreateRawlsBillingProjectFullRequest) = {
      helper.createBillingProjectFull(createProjectRequest)
  }

  /**
   * grant a google role to a user and their pet in the billing project the caller owns
   * 
   *
   * @param projectId Project ID 
   * @param role google role of user for project 
   * @param email email of user 
   * @return void
   */
  def grantGoogleRoleToUser(projectId: String, role: String, email: String) = {
    val await = Try(Await.result(grantGoogleRoleToUserAsync(projectId, role, email), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * grant a google role to a user and their pet in the billing project the caller owns asynchronously
   * 
   *
   * @param projectId Project ID 
   * @param role google role of user for project 
   * @param email email of user 
   * @return Future(void)
   */
  def grantGoogleRoleToUserAsync(projectId: String, role: String, email: String) = {
      helper.grantGoogleRoleToUser(projectId, role, email)
  }

  /**
   * list members of billing project the caller owns
   * 
   *
   * @param projectId Project ID 
   * @return List[BillingProjectMember]
   */
  def listBillingProjectMembers(projectId: String): Option[List[BillingProjectMember]] = {
    val await = Try(Await.result(listBillingProjectMembersAsync(projectId), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * list members of billing project the caller owns asynchronously
   * 
   *
   * @param projectId Project ID 
   * @return Future(List[BillingProjectMember])
   */
  def listBillingProjectMembersAsync(projectId: String): Future[List[BillingProjectMember]] = {
      helper.listBillingProjectMembers(projectId)
  }

  /**
   * remove a google role from a user and their pet in the billing project the caller owns
   * 
   *
   * @param projectId Project ID 
   * @param role google role of user for project 
   * @param email email of user 
   * @return void
   */
  def removeGoogleRoleFromUser(projectId: String, role: String, email: String) = {
    val await = Try(Await.result(removeGoogleRoleFromUserAsync(projectId, role, email), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * remove a google role from a user and their pet in the billing project the caller owns asynchronously
   * 
   *
   * @param projectId Project ID 
   * @param role google role of user for project 
   * @param email email of user 
   * @return Future(void)
   */
  def removeGoogleRoleFromUserAsync(projectId: String, role: String, email: String) = {
      helper.removeGoogleRoleFromUser(projectId, role, email)
  }

  /**
   * remove user from billing project the caller owns
   * 
   *
   * @param projectId Project ID 
   * @param workbenchRole role of user for project 
   * @param email email of user or group to remove 
   * @return void
   */
  def removeUserFromBillingProject(projectId: String, workbenchRole: String, email: String) = {
    val await = Try(Await.result(removeUserFromBillingProjectAsync(projectId, workbenchRole, email), Duration.Inf))
    await match {
      case Success(i) => Some(await.get)
      case Failure(t) => None
    }
  }

  /**
   * remove user from billing project the caller owns asynchronously
   * 
   *
   * @param projectId Project ID 
   * @param workbenchRole role of user for project 
   * @param email email of user or group to remove 
   * @return Future(void)
   */
  def removeUserFromBillingProjectAsync(projectId: String, workbenchRole: String, email: String) = {
      helper.removeUserFromBillingProject(projectId, workbenchRole, email)
  }

}

class BillingApiAsyncHelper(client: TransportClient, config: SwaggerConfig) extends ApiClient(client, config) {

  def addUserToBillingProject(projectId: String,
    workbenchRole: String,
    email: String)(implicit reader: ClientResponseReader[Unit]): Future[Unit] = {
    // create path and map variables
    val path = (addFmt("/api/billing/{projectId}/{workbenchRole}/{email}")
      replaceAll("\\{" + "projectId" + "\\}", projectId.toString)
      replaceAll("\\{" + "workbenchRole" + "\\}", workbenchRole.toString)
      replaceAll("\\{" + "email" + "\\}", email.toString))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (projectId == null) throw new Exception("Missing required parameter 'projectId' when calling BillingApi->addUserToBillingProject")

    if (workbenchRole == null) throw new Exception("Missing required parameter 'workbenchRole' when calling BillingApi->addUserToBillingProject")

    if (email == null) throw new Exception("Missing required parameter 'email' when calling BillingApi->addUserToBillingProject")


    val resFuture = client.submit("PUT", path, queryParams.toMap, headerParams.toMap, "")
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }

  def createBillingProjectFull(createProjectRequest: CreateRawlsBillingProjectFullRequest)(implicit reader: ClientResponseReader[Unit], writer: RequestWriter[CreateRawlsBillingProjectFullRequest]): Future[Unit] = {
    // create path and map variables
    val path = (addFmt("/api/billing"))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (createProjectRequest == null) throw new Exception("Missing required parameter 'createProjectRequest' when calling BillingApi->createBillingProjectFull")

    val resFuture = client.submit("POST", path, queryParams.toMap, headerParams.toMap, writer.write(createProjectRequest))
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }

  def grantGoogleRoleToUser(projectId: String,
    role: String,
    email: String)(implicit reader: ClientResponseReader[Unit]): Future[Unit] = {
    // create path and map variables
    val path = (addFmt("/api/billing/{projectId}/googleRole/{role}/{email}")
      replaceAll("\\{" + "projectId" + "\\}", projectId.toString)
      replaceAll("\\{" + "role" + "\\}", role.toString)
      replaceAll("\\{" + "email" + "\\}", email.toString))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (projectId == null) throw new Exception("Missing required parameter 'projectId' when calling BillingApi->grantGoogleRoleToUser")

    if (role == null) throw new Exception("Missing required parameter 'role' when calling BillingApi->grantGoogleRoleToUser")

    if (email == null) throw new Exception("Missing required parameter 'email' when calling BillingApi->grantGoogleRoleToUser")


    val resFuture = client.submit("PUT", path, queryParams.toMap, headerParams.toMap, "")
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }

  def listBillingProjectMembers(projectId: String)(implicit reader: ClientResponseReader[List[BillingProjectMember]]): Future[List[BillingProjectMember]] = {
    // create path and map variables
    val path = (addFmt("/api/billing/{projectId}/members")
      replaceAll("\\{" + "projectId" + "\\}", projectId.toString))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (projectId == null) throw new Exception("Missing required parameter 'projectId' when calling BillingApi->listBillingProjectMembers")


    val resFuture = client.submit("GET", path, queryParams.toMap, headerParams.toMap, "")
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }

  def removeGoogleRoleFromUser(projectId: String,
    role: String,
    email: String)(implicit reader: ClientResponseReader[Unit]): Future[Unit] = {
    // create path and map variables
    val path = (addFmt("/api/billing/{projectId}/googleRole/{role}/{email}")
      replaceAll("\\{" + "projectId" + "\\}", projectId.toString)
      replaceAll("\\{" + "role" + "\\}", role.toString)
      replaceAll("\\{" + "email" + "\\}", email.toString))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (projectId == null) throw new Exception("Missing required parameter 'projectId' when calling BillingApi->removeGoogleRoleFromUser")

    if (role == null) throw new Exception("Missing required parameter 'role' when calling BillingApi->removeGoogleRoleFromUser")

    if (email == null) throw new Exception("Missing required parameter 'email' when calling BillingApi->removeGoogleRoleFromUser")


    val resFuture = client.submit("DELETE", path, queryParams.toMap, headerParams.toMap, "")
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }

  def removeUserFromBillingProject(projectId: String,
    workbenchRole: String,
    email: String)(implicit reader: ClientResponseReader[Unit]): Future[Unit] = {
    // create path and map variables
    val path = (addFmt("/api/billing/{projectId}/{workbenchRole}/{email}")
      replaceAll("\\{" + "projectId" + "\\}", projectId.toString)
      replaceAll("\\{" + "workbenchRole" + "\\}", workbenchRole.toString)
      replaceAll("\\{" + "email" + "\\}", email.toString))

    // query params
    val queryParams = new mutable.HashMap[String, String]
    val headerParams = new mutable.HashMap[String, String]

    if (projectId == null) throw new Exception("Missing required parameter 'projectId' when calling BillingApi->removeUserFromBillingProject")

    if (workbenchRole == null) throw new Exception("Missing required parameter 'workbenchRole' when calling BillingApi->removeUserFromBillingProject")

    if (email == null) throw new Exception("Missing required parameter 'email' when calling BillingApi->removeUserFromBillingProject")


    val resFuture = client.submit("DELETE", path, queryParams.toMap, headerParams.toMap, "")
    resFuture flatMap { resp =>
      process(reader.read(resp))
    }
  }


}