package CaseClassTesting

import api.WSClientN
import play.api.libs.json.{JsObject, JsValue, Json}

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object CurrenciesTest extends App {

  def getAllCountryData(country: String) = {


    def clientForCountryData(country: String) = {
      val url = s"https://restcountries.com/v3.1/name/$country"
      WSClientN.client
        .url(url)
        .get()
        .map { response =>
          response.status match {
            case 200 =>
              val resultFromClient = response.body[JsValue].as[Seq[JsObject]]
              resultFromClient
            case _ =>
              val errorMsg = s"Error: ${response.status} ${response.statusText}"
              new Exception(errorMsg)
              Seq()
          }
        }
    }

    val allCountries = Await.result(clientForCountryData(country), 1 minutes) match {
      case result => result.mkString("")
    }

    val data = Json.parse(allCountries).as[CountryDataParsing]
    // PARSING THE CURRENCY
    val currency = data.currencies.map(x => x.values).get.head.getOrElse("name", " ")

    println(currency)

  }
  getAllCountryData("USA")
}
