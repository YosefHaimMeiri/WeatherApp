package com.example.weatherapponerobotixyossimeiri.models

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id")          val weatherConditionId: Int,
    @SerializedName("main")        val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon")        val icon: String
)