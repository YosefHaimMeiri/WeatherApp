package com.example.weatherapponerobotixyossimeiri.utils

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
    }
}