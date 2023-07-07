package CaseClassTesting

import ai.x.play.json.Encoders.encoder
import ai.x.play.json.Jsonx
import play.api.libs.json.{Format, JsValue}
// PARSING THE JSON RESULT FROM  https://restcountries.com/v3.1/name/France
// THIS CASE CLASS IS USED IN CurrenciesTest under CaseClassTesting

case class CountryName(common: String, official: String, nativeName: Map[String, Map[String, String]])
object CountryName {
  implicit val format: Format[CountryName] = Jsonx.formatCaseClass[CountryName]
}

case class CountryDataParsing(
                               name: Option[CountryName],
                               tld: Option[List[String]],
                               cca2: Option[String],
                               ccn3: Option[String],
                               cca3: Option[String],
                               independent: Option[Boolean],
                               status: Option[String],
                               unMember: Option[Boolean],
                               currencies: Option[Map[String, Map[String,String]]],
                               idd: Option[Map[String, JsValue]],
                               capital: Option[List[String]],
                               altSpellings: Option[List[String]],
                               region: Option[String],
                               subregion: Option[String],
                               languages: Option[Map[String,String]],
                               translations: Option[Map[String, Map[String,String]]],
                               latlng: Option[List[Double]],
                               landlocked: Option[Boolean],
                               borders: Option[List[String]],
                               area: Option[Double],
                               demonyms: Option[Map[String, Map[String,String]]],
                               flag: Option[String],
                               maps: Option[Map[String,String]],
                               population: Option[Double],
                               gini: Option[Map[String,Double]],
                               fifa: Option[String],
                               car: Option[Map[String,JsValue]],
                               timezones:Option[List[String]],
                               continents: Option[List[String]],
                               flags: Option[Map[String,String]],
                               coatOfArms: Option[Map[String,String]],
                               startOfWeek: Option[String],
                               capitalInfo: Option[Map[String,List[Double]]],
                               postalCode: Option[Map[String,String]]
                             )
object CountryDataParsing {
  implicit val format: Format[CountryDataParsing] = Jsonx.formatCaseClass[CountryDataParsing]

}
