package com.example.weatherapponerobotixyossimeiri.utils

import com.example.weatherapponerobotixyossimeiri.models.DailyWeatherResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GenericUtils {

    companion object {
        val iconUrlPlaceholder = "https://openweathermap.org/img/wn/%s.png";

        /**
         * Input: Double representation of temperature measured in Kelvin
         * Output: Int representation of temperature measured in celsius
         */
        fun kelvinToCelsius(tempKelvin: Double): Int {
            return (tempKelvin - 273.15).toInt()
        }

        /**
         * Alternative to .capitalize
         */
        fun capitalizeEveryWord(input: String): String {
            return input.split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
        }

        /**
         * Input: List of daily weather data
         * Output: List of daily weather data, filtered to have only one object for each day
         */
        fun filterForecastData(list: List<DailyWeatherResponse>): List<DailyWeatherResponse> {
            val retVal = mutableListOf<DailyWeatherResponse>()


            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            var previousDate = ""

            for (item in list) {
                val date = Date(item.dt*1000) // Convert timestamp to Date
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