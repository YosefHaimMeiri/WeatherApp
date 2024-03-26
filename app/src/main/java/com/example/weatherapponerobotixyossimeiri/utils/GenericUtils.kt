package com.example.weatherapponerobotixyossimeiri.utils

class GenericUtils {

    companion object {
        fun kelvinToCelsius(tempKelvin: Double): Int {
            return (tempKelvin - 273.15).toInt()
        }

        fun capitalizeEveryWord(input: String): String {
            return input.split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
        }
    }


}