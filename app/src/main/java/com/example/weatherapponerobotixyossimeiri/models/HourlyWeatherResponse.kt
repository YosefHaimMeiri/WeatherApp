package com.example.weatherapponerobotixyossimeiri.models

import com.google.gson.annotations.SerializedName

data class HourlyWeatherResponse(
    @SerializedName("dt")         val dt: Long,
    @SerializedName("temp")       val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("pressure")   val pressure: Int,
    @SerializedName("humidity")   val humidity: Int,
    @SerializedName("dew_point")  val dewPoint: Double,
    @SerializedName("uvi")        val uvi: Double,
    @SerializedName("clouds")     val clouds: Int,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_deg")   val windDeg: Int,
    @SerializedName("wind_gust")  val windGust: Double,
    @SerializedName("weather")    val weather: List<WeatherConditionsResponse>,
    @SerializedName("pop")        val pop: Double
)
