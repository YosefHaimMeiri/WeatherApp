package com.example.weatherapponerobotixyossimeiri.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.example.weatherapponerobotixyossimeiri.models.DailyWeatherAndForecastResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import com.example.weatherapponerobotixyossimeiri.repos.WeatherDataRepo
import com.example.weatherapponerobotixyossimeiri.services.IWeatherApiService
import com.example.weatherapponerobotixyossimeiri.services.WeatherApiClient
import com.google.gson.Gson
import retrofit2.Call

class WeatherViewModel(val repo : WeatherDataRepo) : ViewModel(){
    // Cached data
    var TAG = "WeatherViewModel"
    var currentWeatherData: WeatherDataResponse? = null
    var forecastWeatherData: DailyWeatherAndForecastResponse? = null

    constructor() : this(WeatherDataRepo(WeatherApiClient.getClient().create(IWeatherApiService::class.java)))

    fun loadCurrentWeatherByCoordinates(lat: Double, lon: Double): Call<WeatherDataResponse> {
        return repo.getCurrentWeatherByCoordinates(lat,lon);
    }

    fun loadCurrentWeatherAndForecastByCoordinates(lat: Double, lon: Double): Call<DailyWeatherAndForecastResponse> {
        return repo.getDailyWeatherAndForecastByCoordinates(lat,lon);
    }

}