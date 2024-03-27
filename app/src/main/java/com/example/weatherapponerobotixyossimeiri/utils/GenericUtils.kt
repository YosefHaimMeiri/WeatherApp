package com.example.weatherapponerobotixyossimeiri.utils

import com.example.weatherapponerobotixyossimeiri.models.Daily
import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.tan

class GenericUtils {

    companion object {
        fun kelvinToCelsius(tempKelvin: Double): Int {
            return (tempKelvin - 273.15).toInt()
        }

        fun capitalizeEveryWord(input: String): String {
            return input.split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
        }

        fun filterForecastData(list: List<Daily>): List<Daily> {
            val retVal = mutableListOf<Daily>()


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