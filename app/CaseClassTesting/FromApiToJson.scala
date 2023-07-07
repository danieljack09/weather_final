package CaseClassTesting

import play.api.libs.json.{Format, Json}

case class FromApiToJson(
                      id: Option[Long],
                      name: Option[String],
                      latitude: Option[Double],
                      longitude: Option[Double],
                      elevation: Option[Double],
                      featureCode: Option[String],
                      countryCode: Option[String],
                      adminId: Option[Long],
                      timezone: Option[String],
                      population: Option[Long],
                      countryId: Option[Long],
                      country: Option[String],
                      admin: Option[String]
                        )
object FromApiToJson{
  implicit val format:Format[FromApiToJson] = Json.format[FromApiToJson]
}