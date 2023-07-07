package CaseClassTesting

import api.WSClientN
import play.api.libs.json._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object Testing extends App {

  def cityResult(city: String) = {
    // CLIENT
    def clientForCity(city: String) = {
      val apiUrl = s"https://geocoding-api.open-meteo.com/v1/search?name=$city&count=1"
      WSClientN.client
        .url(apiUrl)
        .get()
        .map { response =>
          response.status match {
            case 200 =>
              val resultFromResponse = (response.body[JsValue].as[JsObject] \ "results").as[Seq[JsObject]]
              resultFromResponse
            case _ =>
              val errorMessage = s"Error: ${response.status} ${response.statusText}"
              new Exception(errorMessage)
              Seq()
          }
        }
    }
    val cityRes = Await.result(clientForCity(city), 10 minutes) match {
      case List(response) => response.toString()
    }
    val data = Json.parse(cityRes).as[FromApiToJson]
  }
  def resForW(url:String) = {
    def clientForWeather(url: String) = {
      val result = WSClientN.client
        .url(url)
        .get()
        .map { response =>
          response.status match {
            case 200 =>
              val resultati = response.body[JsValue].as[JsObject]
              resultati
            case _ =>
              val errorMessage = s"Error: ${response.status} ${response.statusText}"
              new Exception(errorMessage)
              Seq()
          }
        }
      result
    }


    // DATA FROM CLIENT

    val resultFromWeather = Await.result(clientForWeather(url), 10 minutes) match {
      case result => result.toString
    }

    // PARSING THE DATA USING FORMATTER - WeatherDataToJson
    val data = Json.parse(resultFromWeather).as[WeatherDataToJson]


    //SAMPLE DATA FROM JSOBJECT
    val weather = data.current_weather.get.temperature.getOrElse("")
    println(weather)
  }




  def defineUrl(lat: String, lon: String): String = {
    val url: String = s"https://api.open-meteo.com/v1/forecast?latitude=$lat&longitude=$lon&current_weather=true"
    url
  }
  resForW(defineUrl("41.99646", "21.4314"))

  val weatData = CurrentWeather(Some(15.0),Some(16.0),Some(17.0),Some(4),Some(1),Some("15:00"))
  val newData = WeatherDataToJson(Some(1.0),Some(2.3),Some(4.333),Some(3),Some("15"),Some("16"),Some(4.0),Some(weatData))


  println(newData)

}
