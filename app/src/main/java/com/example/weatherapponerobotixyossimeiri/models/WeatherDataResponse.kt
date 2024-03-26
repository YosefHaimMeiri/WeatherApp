package com.example.weatherapponerobotixyossimeiri.models

import com.google.gson.annotations.SerializedName

data class WeatherDataResponse(
    @SerializedName("base")        val baseDataSource: String, // E.G. "Stations"
    @SerializedName("main")        val mainTemperatureData : MainTemperatureData,
    @SerializedName("coord")       val coordinates: Coordinates,
    @SerializedName("weather")     val weather: List<Weather>,
    @SerializedName("visibility")  val visibility: Int,
    @SerializedName("wind")        val wind: Wind,
    @SerializedName("clouds")      val clouds: Clouds,
    @SerializedName("dt")          val dateTime: Long,
    @SerializedName("sys")         val systemInfo: SystemInformation,
    @SerializedName("timezone")    val timezone: Int,
    @SerializedName("id")          val cityId: Int,
    @SerializedName("name")        val cityName: String,
    @SerializedName("cod")         val code: Int
)

data class Coordinates(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)

data class Weather(
    @SerializedName("id")          val weatherConditionId: Int,
    @SerializedName("main")        val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon")        val icon: String
)

data class MainTemperatureData(
    @SerializedName("temp")       val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min")   val tempMin: Double,
    @SerializedName("temp_max")   val tempMax: Double,
    @SerializedName("pressure")   val pressure: Int,
    @SerializedName("humidity")   val humidity: Int
)

data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg")   val deg: Int
)

data class Clouds(
    @SerializedName("all") val all: Int
)

data class SystemInformation(
    @SerializedName("type")    val type: Int,
    @SerializedName("id")      val id: Int,
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset")  val sunset: Int
)

