package com.example.weatherapponerobotixyossimeiri.viewmodels

import com.example.weatherapponerobotixyossimeiri.repos.WeatherIconRepo
import com.example.weatherapponerobotixyossimeiri.services.IWeatherIconApiService
import com.example.weatherapponerobotixyossimeiri.services.WeatherIconApiClient

class WeatherIconViewModel(val repo : WeatherIconRepo) {

    constructor() : this(WeatherIconRepo(WeatherIconApiClient.getClient().create(IWeatherIconApiService::class.java)))

    fun loadIcon(iconString: String) {
        return repo.getWeatherIconByCode(iconString)
    }
}