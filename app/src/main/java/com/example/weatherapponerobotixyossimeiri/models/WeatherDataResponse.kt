package com.example.weatherapponerobotixyossimeiri.models

import com.google.gson.annotations.SerializedName

data class WeatherDataResponse(
    @SerializedName("dt")          val dateTime: Long,
    @SerializedName("main")        val mainTemperatureData : MainTemperatureData,
    @SerializedName("weather")     val weather: List<Weather>,
    @SerializedName("clouds")      val clouds: Clouds,
    @SerializedName("wind")        val wind: Wind,
    @SerializedName("visibility")  val visibility: Int,
    @SerializedName("sys")         val systemInfo: SystemInformation,


    /**
     * From here -> Fields that are unavailable in the forecast data
     */
    @SerializedName("base")        val baseDataSource: String, // E.G. "Stations"
    @SerializedName("coord")       val coordinates: Coordinates,
    @SerializedName("timezone")    val timezone: Int,
    @SerializedName("id")          val cityId: Int,
    @SerializedName("name")        val cityName: String,
    @SerializedName("cod")         val code: Int,

    /**
     * This field is ONLY available in the forecast data!
     */
    @SerializedName("dt_text") val dateTimeText: String

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

