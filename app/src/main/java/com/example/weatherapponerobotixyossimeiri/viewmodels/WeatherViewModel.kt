package com.example.weatherapponerobotixyossimeiri.viewmodels

import androidx.lifecycle.ViewModel
import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherForecastResponse
import com.example.weatherapponerobotixyossimeiri.repos.WeatherDataRepo
import com.example.weatherapponerobotixyossimeiri.services.IWeatherApiService
import com.example.weatherapponerobotixyossimeiri.services.WeatherApiClient
import retrofit2.Call

class WeatherViewModel(val repo : WeatherDataRepo) : ViewModel(){
    constructor() : this(WeatherDataRepo(WeatherApiClient.getClient().create(IWeatherApiService::class.java)))

    fun loadCurrentWeatherByCoordinates(lat: Double, lon: Double): Call<WeatherDataResponse> {
        return repo.getCurrentWeatherByCoordinates(lat,lon);
    }


    fun loadForecastByCoordinates(lat: Double, lon: Double): Call<WeatherForecastResponse> {
        return repo.getForecastByCoordinates(lat,lon);
    }

    fun loadCurrentWeatherByCityCountry(city: String, country: String): Call<WeatherDataResponse> {
        return repo.getCurrentWeatherByCityCountry(city,country);
    }
}