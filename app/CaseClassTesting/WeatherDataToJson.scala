package CaseClassTesting

import play.api.libs.json.{Format, Json}


// PARSING RESULT FROM JSON RESPONSE FROM API https://api.open-meteo.com/v1/forecast?latitude=41.99646&longitude=21.4314&current_weather=true
// THIS CASE CLASS IS USED IN Testing under CaseClassTesting

case class WeatherDataToJson(
                            latitude:Option[Double],
                            longitude:Option[Double],
                            generationTime:Option[Double],
                            utc_offset_seconds:Option[Int],
                            timezone:Option[String],
                            timezoneAbb:Option[String],
                            elevation:Option[Double],
                            current_weather:Option[CurrentWeather]
                            )
object WeatherDataToJson{
  implicit val format:Format[WeatherDataToJson] = Json.format[WeatherDataToJson]
}

case class CurrentWeather(
                         temperature:Option[Double],
                         windspeed: Option[Double],
                         winddirection: Option[Double],
                         weathercode: Option[Int],
                         is_day: Option[Int],
                         time: Option[String]
                         )
object CurrentWeather {
  implicit val format:Format[CurrentWeather] = Json.format[CurrentWeather]
}