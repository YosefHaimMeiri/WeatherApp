package com.example.weatherapponerobotixyossimeiri.models

import com.google.gson.annotations.SerializedName

data class DailyWeatherAndForecastResponse(
    @SerializedName("current")          val current: ForecastDataResponse,
    @SerializedName("daily")            val daily: List<DailyWeatherResponse>,
    @SerializedName("lat")              val lat: Double,
    @SerializedName("lon")              val lon: Double,
    @SerializedName("hourly")           val hourly: List<HourlyWeatherResponse>,
    @SerializedName("timezone")         val timezone: String,
    @SerializedName("timezone_offset")  val timezone_offset: Int
)