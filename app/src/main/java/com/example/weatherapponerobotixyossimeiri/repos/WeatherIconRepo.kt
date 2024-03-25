package com.example.weatherapponerobotixyossimeiri.repos

import com.example.weatherapponerobotixyossimeiri.services.IWeatherIconApiService
import com.example.weatherapponerobotixyossimeiri.utils.WeatherCodeUtils

class WeatherIconRepo(private val service : IWeatherIconApiService ) {
    fun getWeatherIconByCode(code : String) {
        if (WeatherCodeUtils.weatherCodeToIconIdMap.contains(code.toInt())) {
            service.getWeatherIcon(code);
        }
    }
}