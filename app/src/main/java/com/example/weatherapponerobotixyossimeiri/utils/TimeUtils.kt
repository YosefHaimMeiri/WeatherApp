package com.example.weatherapponerobotixyossimeiri.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class TimeUtils {


    companion object {
        val MINUTES = 5 // Minutes since last update


        /**
         * Function to check if MINUTES passed since the last time we updated the data
         */
        fun isMinutesPassed(lastTimeMillis: Long): Boolean {
            var retVal : Boolean

            val currentTimeMillis = System.currentTimeMillis()
            val fiveMinutesInMillis =  MINUTES * 60 * 1000 // MINUTES in milliseconds
            retVal = currentTimeMillis - lastTimeMillis >= fiveMinutesInMillis

            return retVal
        }

        fun convertUnixTimestampToHour(unixTimestamp: Long): String {
            val formatter = SimpleDateFormat("HH:mm")
            val date = Date(unixTimestamp)
            return formatter.format(date)
        }

    }
}