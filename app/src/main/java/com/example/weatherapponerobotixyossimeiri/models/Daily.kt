package com.example.weatherapponerobotixyossimeiri.models

import com.google.gson.annotations.SerializedName

data class Daily(
    @SerializedName("clouds")       val clouds: Double,
    @SerializedName("dew_point")    val dew_point: Double,
    @SerializedName("dt")           val dt: Long,
    @SerializedName("humidity")     val humidity: Double,
    @SerializedName("moon_phase")   val moon_phase: Double,
    @SerializedName("moonrise")     val moonrise: Double,
    @SerializedName("moonset")      val moonset: Double,
    @SerializedName("pop")          val pop: Double,
    @SerializedName("temp")         val temp: TemperatureDataResponse,
    @SerializedName("pressure")     val pressure: Double,
    @SerializedName("summary")      val summary: String,
    @SerializedName("sunrise")      val sunrise: Double,
    @SerializedName("sunset")       val sunset: Double,
    @SerializedName("uvi")          val uvi: Double,
    @SerializedName("weather")      val weather: List<WeatherConditionsResponse>,
    @SerializedName("wind_deg")     val wind_deg: Double,
    @SerializedName("wind_gust")    val wind_gust: Double,
    @SerializedName("wind_speed")   val wind_speed: Double
)