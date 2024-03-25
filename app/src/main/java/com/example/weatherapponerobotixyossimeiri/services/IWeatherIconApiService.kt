package com.example.weatherapponerobotixyossimeiri.services

import com.example.weatherapponerobotixyossimeiri.models.WeatherData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IWeatherIconApiService {
    @GET("img/wn/{iconId}.png")
    fun getWeatherIcon(@Path("iconId") iconId : String): Call<ResponseBody>
}