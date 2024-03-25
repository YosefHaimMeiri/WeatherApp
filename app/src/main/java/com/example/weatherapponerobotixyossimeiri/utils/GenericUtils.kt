package com.example.weatherapponerobotixyossimeiri.utils

class GenericUtils {

    companion object {
        fun kelvinToCelsius(tempKelvin: Double): Int {
            return (tempKelvin - 273.15).toInt()
        }
    }


}