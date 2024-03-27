package com.example.weatherapponerobotixyossimeiri.services

import com.example.weatherapponerobotixyossimeiri.models.DailyWeatherAndForecastResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApiService {

    // Retrofit GET request to fetch current weather data by coordinates
    @GET("2.5/weather")
    fun getCurrentWeatherByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String?
    ): Call<WeatherDataResponse>

    // Retrofit GET request to fetch current weather and forecast data by coordinates
    @GET("3.0/onecall")
    fun getCurrentWeatherAndForecastByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String?
    ): Call<DailyWeatherAndForecastResponse>
}