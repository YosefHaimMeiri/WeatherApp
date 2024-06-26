package com.example.weatherapponerobotixyossimeiri.models

import com.google.gson.annotations.SerializedName

data class ForecastDataResponse(
    @SerializedName("clouds")     val clouds: Int,
    @SerializedName("dew_point")  val dewPoint: Double,
    @SerializedName("dt")         val dt: Long,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("humidity")   val humidity: Int,
    @SerializedName("pressure")   val pressure: Int,
    @SerializedName("sunrise")    val sunrise: Int,
    @SerializedName("sunset")     val sunset: Int,
    @SerializedName("temp")       val temp: Double,
    @SerializedName("uvi")        val uvi: Double,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("weather")    val weather: List<WeatherConditionsResponse>,
    @SerializedName("wind_deg")   val windDeg: Double,
    @SerializedName("wind_gust")  val windGust: Double,
    @SerializedName("wind_speed") val windSpeed: Double
)
