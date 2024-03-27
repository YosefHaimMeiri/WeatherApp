package com.example.weatherapponerobotixyossimeiri.repos

import com.example.weatherapponerobotixyossimeiri.BuildConfig
import com.example.weatherapponerobotixyossimeiri.models.DailyWeatherAndForecastResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherForecastResponse
import com.example.weatherapponerobotixyossimeiri.services.IWeatherApiService
import retrofit2.Call

class WeatherDataRepo (private val service : IWeatherApiService){

    fun getCurrentWeatherByCoordinates(lat : Double, lon : Double): Call<WeatherDataResponse> {
        return service.getCurrentWeatherByCoordinates(lat,lon, BuildConfig.OPENWEATHER_API_KEY);
    }

    fun getDailyWeatherAndForecastByCoordinates(lat : Double, lon : Double): Call<DailyWeatherAndForecastResponse> {
        return service.getCurrentWeatherAndForecastByCoordinates(lat,lon, BuildConfig.OPENWEATHER_API_KEY);
    }
}