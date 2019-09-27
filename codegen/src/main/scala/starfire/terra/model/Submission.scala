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

package starfire.terra.model

import java.util.Date

case class Submission (
  // SubmissionRequest unique identifier
  submissionId: Option[String] = None,
  // SubmissionRequest date
  submissionDate: Option[Date] = None,
  // user that created the submission
  submitter: Option[String] = None,
  // Method configuration namespace
  methodConfigurationNamespace: Option[String] = None,
  // Method configuration name
  methodConfigurationName: Option[String] = None,
  // Optional if this submission is attached to a method configuration that has no root entity
  submissionEntity: Option[AttributeEntityReference] = None,
  // Status of Workflow(s)
  workflows: Option[List[Workflow]] = None,
  // Status
  status: Option[SubmissionStatus] = None,
  // The compute cost of this submission
  cost: Option[Float] = None,
  // Whether or not to read from cache for this submission
  useCallCache: Option[Boolean] = None,
  // What happens after a task fails. Choose from ContinueWhilePossible and NoNewCalls. Defaults to NoNewCalls if not specified. See Cromwell docs for more information.
  workflowFailureMode: Option[String] = None
)

