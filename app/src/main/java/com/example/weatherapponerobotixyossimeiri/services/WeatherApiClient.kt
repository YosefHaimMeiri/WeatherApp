package com.example.weatherapponerobotixyossimeiri.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Weather API Client - Instantiates a new retrofit client with the weather data API's base url
 */
class WeatherApiClient {
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private lateinit var retrofit: Retrofit

    fun getClient() : Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

}