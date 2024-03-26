package com.example.weatherapponerobotixyossimeiri.utils

import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GenericUtils {

    companion object {
        fun kelvinToCelsius(tempKelvin: Double): Int {
            return (tempKelvin - 273.15).toInt()
        }

        fun capitalizeEveryWord(input: String): String {
            return input.split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
        }

        fun filterForecastData(list: List<WeatherDataResponse>): List<WeatherDataResponse> {
            val retVal = mutableListOf<WeatherDataResponse>()


            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            var previousDate = ""

            for (item in list) {
                val date = Date(item.dateTime*1000) // Convert timestamp to Date
                val currentDate = dateFormat.format(date);

                if (currentDate != previousDate) {
                    retVal.add(item);
                    previousDate = currentDate;
                }

            }
            return retVal
        }
    }


}