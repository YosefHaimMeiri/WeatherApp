package com.example.weatherapponerobotixyossimeiri.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.weatherapponerobotixyossimeiri.models.DailyWeatherAndForecastResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import com.google.gson.Gson


class PreferenceManagerHelper(private val context: Context) {
    private val TAG = "PreferenceManagerHelper"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveWeatherData(forecastDataModel: WeatherDataResponse) {
        Log.d(TAG, "save() is executed.")
        val gson = Gson()
        val forecastDataModelJson = gson.toJson(forecastDataModel)
        editor.putString(DAILY_DATA, forecastDataModelJson).apply()
    }

    fun restoreWeatherData(): WeatherDataResponse? {
        Log.d(TAG, "restore() is executed.")
        val gson = Gson()
        val forecastDataModelJson = sharedPreferences.getString(DAILY_DATA, null)
        Log.d(TAG, "restore() forecastDataModelJson: $forecastDataModelJson.")
        return if (forecastDataModelJson != null) {
            gson.fromJson(forecastDataModelJson, WeatherDataResponse::class.java)
        } else {
            null
        }
    }

    fun saveForecastData(dailyWeatherForecast: DailyWeatherAndForecastResponse) {
        Log.d(TAG, "save() is executed.")
        val gson = Gson()
        val dailyWeatherForecastJson = gson.toJson(dailyWeatherForecast)
        Log.d(TAG, "save() dailyWeatherForecastJson: $dailyWeatherForecastJson.")
        editor.putString(FORECAST_DATA, dailyWeatherForecastJson).apply()
    }

    fun restoreForecastData(): DailyWeatherAndForecastResponse? {
        Log.d(TAG, "restore() is executed.")
        val gson = Gson()
        val dailyWeatherForecastJson = sharedPreferences.getString(FORECAST_DATA, null)
        Log.d(TAG, "restore() dailyWeatherForecastJson: $dailyWeatherForecastJson.")
        return if (dailyWeatherForecastJson != null) {
            gson.fromJson(dailyWeatherForecastJson, DailyWeatherAndForecastResponse::class.java)
        } else {
            null
        }
    }


    companion object {
        private const val PREF_NAME = "SavedData"
        private const val DAILY_DATA = "DailyData"
        private const val FORECAST_DATA = "ForecastData"
    }
}

