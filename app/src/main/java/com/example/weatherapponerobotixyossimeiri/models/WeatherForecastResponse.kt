package com.example.weatherapponerobotixyossimeiri.models

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse(
    @SerializedName("cod") val cod: String,
    @SerializedName("message") val message: Int,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val list: List<WeatherDataResponse>,
    @SerializedName("city") val city: CityInfo
)
data class WeatherInfo(
    val id: Long,
    val main: String, // Weather condition (e.g., Clear, Clouds, Rain, etc.)
    val description: String, // Weather description (e.g., clear sky, scattered clouds, light rain, etc.)
    val icon: String, // Weather icon ID
    val temp: Double, // Temperature in Kelvin
    val feelsLike: Double, // Temperature feels like in Kelvin
    val tempMin: Double, // Minimum temperature in Kelvin
    val tempMax: Double, // Maximum temperature in Kelvin
    val pressure: Int, // Atmospheric pressure in hPa
    val humidity: Int, // Humidity percentage
    val windSpeed: Double, // Wind speed in meters per second
    val windDeg: Int, // Wind direction in degrees
    val clouds: Int, // Cloudiness percentage
    val visibility: Int, // Visibility in meters
    val sunrise: Long, // Sunrise time in UNIX timestamp
    val sunset: Long // Sunset time in UNIX timestamp
)

data class CityInfo(
    val id: Int,
    val name: String,
    val coord: Coordinates,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)
