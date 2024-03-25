package com.example.weatherapponerobotixyossimeiri.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Weather API Client - Instantiates a new retrofit client with the weather data API's ((ICONS)) base url
 */
class WeatherIconApiClient {
    companion object {
        private val BASE_URL = "https://openweathermap.org/img/wn/"

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
}