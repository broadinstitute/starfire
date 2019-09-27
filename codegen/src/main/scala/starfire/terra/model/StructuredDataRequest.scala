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


case class StructuredDataRequest (
  // Is the data available for future general research use?
  generalResearchUse: Boolean,
  // Is future use limited for health/medical/biomedical research?
  healthMedicalBiomedicalUseRequired: Boolean,
  // Future use is limited to research involving the following disease area(s). These values must be urls such as \"http://purl.obolibrary.org/obo/DOID_12345\".
  diseaseUseRequired: List[String],
  // Is future commercial use prohibited?
  commercialUseProhibited: Boolean,
  // Is future use by for-profit entities prohibited?
  forProfitUseProhibited: Boolean,
  // Is future use for methods research (analytic/software/technology development) outside the bounds of the other specified restrictions prohibited?
  methodsResearchProhibited: Boolean,
  // Is future use of aggregate-level data for general research purposes prohibited?
  aggregateLevelDataProhibited: Boolean,
  // Is future use as a control set for diseases other than those specified prohibited?
  controlsUseProhibited: Boolean,
  // If future use is limited to research involving a particular gender, specify \"male\" or \"female\". Any other value will be read as false. This field is case-insensitive.
  genderUseRequired: String,
  // Is future use limited to pediatric research?
  pediatricResearchRequired: Boolean,
  // Should the requester provide documentation of local IRB/REC approval?
  irbRequired: Boolean,
  // Optional. A prefix that is prepended to the field names of the responses. This is purely for personalization.
  prefix: Option[String] = None
)

