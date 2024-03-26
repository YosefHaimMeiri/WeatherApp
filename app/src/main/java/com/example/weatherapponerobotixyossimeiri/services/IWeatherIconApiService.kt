package com.example.weatherapponerobotixyossimeiri.services

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IWeatherIconApiService {
    @GET("img/wn/{iconId}.png")
    fun getWeatherIcon(@Path("iconId") iconId : String): Call<ResponseBody>
}