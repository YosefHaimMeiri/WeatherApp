package com.example.weatherapponerobotixyossimeiri.viewmodels

import com.example.weatherapponerobotixyossimeiri.repos.WeatherIconRepo
import com.example.weatherapponerobotixyossimeiri.services.IWeatherIconApiService
import com.example.weatherapponerobotixyossimeiri.services.WeatherIconApiClient
import okhttp3.ResponseBody
import retrofit2.Call

class WeatherIconViewModel(val repo : WeatherIconRepo) {

    constructor() : this(WeatherIconRepo(WeatherIconApiClient.getClient().create(IWeatherIconApiService::class.java)))

    fun loadIcon(iconString: String): Call<ResponseBody> {
        return repo.getWeatherIconByCode(iconString)
    }
}