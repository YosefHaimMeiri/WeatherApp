package com.example.weatherapponerobotixyossimeiri.repos

import android.util.Log
import com.example.weatherapponerobotixyossimeiri.BuildConfig
import com.example.weatherapponerobotixyossimeiri.models.DailyWeatherAndForecastResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import com.example.weatherapponerobotixyossimeiri.services.IWeatherApiService
import retrofit2.Call

class WeatherDataRepo (private val service : IWeatherApiService){
    private val TAG = "WeatherDataRepo"

    fun getCurrentWeatherByCoordinates(lat : Double, lon : Double): Call<WeatherDataResponse> {
        Log.d(TAG, "Fetching current weather data from API for coordinates: $lat, $lon")
        return service.getCurrentWeatherByCoordinates(lat,lon, BuildConfig.OPENWEATHER_API_KEY);
    }

    fun getDailyWeatherAndForecastByCoordinates(lat : Double, lon : Double): Call<DailyWeatherAndForecastResponse> {
        Log.d(TAG, "Fetching forecast data from API for coordinates: $lat, $lon")
        return service.getCurrentWeatherAndForecastByCoordinates(lat,lon, BuildConfig.OPENWEATHER_API_KEY);
    }
}