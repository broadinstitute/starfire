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


case class Tool (
  // The URL for this tool in this registry, for example `http://agora.broadinstitute.org/tools/123456`
  url: String,
  // A unique identifier of the tool, scoped to this registry, for example `123456` or `123456_v1`
  id: String,
  // The organization that published the image.
  organization: String,
  // The name of the tool.
  toolname: Option[String] = None,
  toolclass: ToolClass,
  // The description of the tool.
  description: Option[String] = None,
  // Contact information for the author of this tool entry in the registry. (More complex authorship information is handled by the descriptor)
  author: String,
  // The version of this tool in the registry. Iterates when fields like the description, author, etc. are updated.
  metaVersion: String,
  // An array of IDs for the applications that are stored inside this tool (for example `https://bio.tools/tool/mytum.de/SNAP2/1`). This always returns an empty array.
  contains: Option[List[String]] = None,
  // Reports whether this tool has been verified by a specific organization or individual.  This always returns false.
  verified: Option[Boolean] = None,
  // Source of metadata that can support a verified tool, such as an email or URL
  verifiedSource: Option[String] = None,
  // Reports whether this tool has been signed.  This always returns false.
  signed: Option[Boolean] = None,
  // A list of versions for this tool
  versions: List[ToolVersion]
)

