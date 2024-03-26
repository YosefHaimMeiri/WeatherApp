package com.example.weatherapponerobotixyossimeiri.utils

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
        const val zoomLevel = 0;

        fun kelvinToCelsius(tempKelvin: Double): Int {
            return (tempKelvin - 273.15).toInt()
        }

        fun capitalizeEveryWord(input: String): String {
            return input.split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
        }

        fun latLonToTileCoordinates(lat: Double, lon: Double): Pair<Int, Int> {
            val xTile = ((lon + 180) / 360 * (1 shl zoomLevel)).toInt()
            val yTile = ((1 - ln(tan(lat * PI / 180) + 1 / cos(lat * PI / 180)) / PI) / 2 * (1 shl zoomLevel)).toInt()
            return Pair(xTile, yTile)
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