package com.example.weatherapponerobotixyossimeiri.repos

import com.example.weatherapponerobotixyossimeiri.BuildConfig
import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherForecastResponse
import com.example.weatherapponerobotixyossimeiri.services.IWeatherApiService
import retrofit2.Call

class WeatherDataRepo (private val service : IWeatherApiService){

    fun getCurrentWeatherByCoordinates(lat : Double, lon : Double): Call<WeatherDataResponse> {
        return service.getCurrentWeatherByCoordinates(lat,lon, BuildConfig.OPENWEATHER_API_KEY);
    }

    fun getForecastByCoordinates(lat : Double, lon : Double): Call<WeatherForecastResponse> {
        return service.getForecastByCoordinates(lat,lon, BuildConfig.OPENWEATHER_API_KEY);
    }

    fun getCurrentWeatherByCityCountry(city: String, country: String): Call<WeatherDataResponse> {
        var queryString = String.format("%s,%s",city,country);
        return service.getCurrentWeatherByCityAndCountry(queryString, BuildConfig.OPENWEATHER_API_KEY);
    }

}