package com.example.weatherapponerobotixyossimeiri.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Weather API Client - Instantiates a new retrofit client with the weather data API's base url
 */
class WeatherApiClient {


    companion object {
        private lateinit var retrofit: Retrofit
        private val BASE_URL = "https://api.openweathermap.org/data/"

        fun getClient() : Retrofit {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }




}