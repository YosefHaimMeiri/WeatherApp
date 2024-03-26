package com.example.weatherapponerobotixyossimeiri.models

data class Daily(
    val clouds: Double,
    val dew_point: Double,
    val dt: Long,
    val feels_like: FeelsLike,
    val humidity: Double,
    val moon_phase: Double,
    val moonrise: Double,
    val moonset: Double,
    val pop: Double,
    val pressure: Double,
    val summary: String,
    val sunrise: Double,
    val sunset: Double,
    val temp: Temp,
    val uvi: Double,
    val weather: List<Weather>,
    val wind_deg: Double,
    val wind_gust: Double,
    val wind_speed: Double
)