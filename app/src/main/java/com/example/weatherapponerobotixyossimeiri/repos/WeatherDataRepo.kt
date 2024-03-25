package com.example.weatherapponerobotixyossimeiri.repos

import com.example.weatherapponerobotixyossimeiri.BuildConfig
import com.example.weatherapponerobotixyossimeiri.models.WeatherData
import com.example.weatherapponerobotixyossimeiri.services.IWeatherApiService
import retrofit2.Call

class WeatherDataRepo (private val service : IWeatherApiService){

    fun getCurrentWeatherByCoordinates(lat : Double, lon : Double): Call<WeatherData> {
        return service.getCurrentWeatherByCoordinates(lat,lon, BuildConfig.OPENWEATHER_API_KEY);
    }

    fun getCurrentWeatherByCityCountry(city: String, country: String): Call<WeatherData> {
        var queryString = String.format("%s,%s",city,country);
        return service.getCurrentWeatherByCityAndCountry(queryString, BuildConfig.OPENWEATHER_API_KEY);
    }

}