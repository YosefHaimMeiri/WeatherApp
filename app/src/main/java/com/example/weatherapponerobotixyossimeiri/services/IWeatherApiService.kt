package com.example.weatherapponerobotixyossimeiri.services

import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApiService {
    @GET("weather")
    fun getCurrentWeatherByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String?
    ): Call<WeatherDataResponse>

    @GET("forecast")
    fun getForecastByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String?
    ): Call<WeatherForecastResponse>

    @GET("weather")
    fun getCurrentWeatherByCityAndCountry(
        @Query("q") cityCountry: String?,
        @Query("appid") apiKey: String?
    ): Call<WeatherDataResponse>
}