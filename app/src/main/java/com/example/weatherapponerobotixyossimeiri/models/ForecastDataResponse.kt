package com.example.weatherapponerobotixyossimeiri.models

data class ForecastDataResponse(
    val clouds: Int,
    val dew_point: Double,
    val dt: Long,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<WeatherConditionsResponse>,
    val wind_deg: Double,
    val wind_gust: Double,
    val wind_speed: Double
)