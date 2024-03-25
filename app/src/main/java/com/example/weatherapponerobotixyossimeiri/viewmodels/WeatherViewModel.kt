package com.example.weatherapponerobotixyossimeiri.viewmodels

import androidx.lifecycle.ViewModel
import com.example.weatherapponerobotixyossimeiri.models.WeatherData
import com.example.weatherapponerobotixyossimeiri.repos.WeatherDataRepo
import com.example.weatherapponerobotixyossimeiri.services.IWeatherApiService
import com.example.weatherapponerobotixyossimeiri.services.WeatherApiClient
import retrofit2.Call

class WeatherViewModel(val repo : WeatherDataRepo) : ViewModel(){
    constructor() : this(WeatherDataRepo(WeatherApiClient.getClient().create(IWeatherApiService::class.java)))

    fun loadCurrentWeatherByCoordinates(lat: Double, lon: Double): Call<WeatherData> {
        return repo.getCurrentWeatherByCoordinates(lat,lon);
    }


    fun loadCurrentWeatherByCityCountry(city: String, country: String): Call<WeatherData> {
        return repo.getCurrentWeatherByCityCountry(city,country);
    }
}