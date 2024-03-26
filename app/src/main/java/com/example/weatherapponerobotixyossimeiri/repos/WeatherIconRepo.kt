package com.example.weatherapponerobotixyossimeiri.repos

import com.example.weatherapponerobotixyossimeiri.services.IWeatherIconApiService
import okhttp3.ResponseBody
import retrofit2.Call

class WeatherIconRepo(private val service : IWeatherIconApiService ) {
    fun getWeatherIconByCode(code : String) : Call<ResponseBody> {
        return service.getWeatherIcon(code);
    }
}