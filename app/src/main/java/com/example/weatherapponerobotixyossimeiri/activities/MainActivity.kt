package com.example.weatherapponerobotixyossimeiri.activities

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import com.example.weatherapponerobotixyossimeiri.R
import com.example.weatherapponerobotixyossimeiri.adapters.ForecastDataAdapter
import com.example.weatherapponerobotixyossimeiri.adapters.HourlyDataAdapter
import com.example.weatherapponerobotixyossimeiri.databinding.ActivityMainBinding
import com.example.weatherapponerobotixyossimeiri.models.DailyWeatherAndForecastResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import com.example.weatherapponerobotixyossimeiri.strings.WeatherStrings
import com.example.weatherapponerobotixyossimeiri.utils.GenericUtils
import com.example.weatherapponerobotixyossimeiri.utils.LocationHelper
import com.example.weatherapponerobotixyossimeiri.utils.PreferenceManagerHelper
import com.example.weatherapponerobotixyossimeiri.utils.TimeUtils
import com.example.weatherapponerobotixyossimeiri.viewmodels.WeatherViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), LocationHelper.LocationChangeListener {

    val TAG = "MainActivity"

    lateinit var locationHelper: LocationHelper;
    lateinit var binding: ActivityMainBinding

    private lateinit var weatherViewModel:WeatherViewModel
    private lateinit var forecastAdapter : ForecastDataAdapter
    private lateinit var hourlyAdapter : HourlyDataAdapter
    private lateinit var preferenceManagerHelper: PreferenceManagerHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
        initializeViewModels()
        initHelpersAndAdapter()
        checkAndUpdateDataAndUI()
    }

    private fun checkAndUpdateDataAndUI() {
        weatherViewModel.currentWeatherData = preferenceManagerHelper.restoreWeatherData();
        weatherViewModel.forecastWeatherData = preferenceManagerHelper.restoreForecastData()

        if (weatherViewModel.currentWeatherData != null && weatherViewModel.forecastWeatherData != null && !isNeedToUpdateData(
                preferenceManagerHelper.restoreUpdateTime()
            )
        ) {
            updateCurrentWeatherUI(weatherViewModel.currentWeatherData!!)
            updateForecastDataUI(weatherViewModel.forecastWeatherData!!)
        } else {
            locationHelper.updateCurrentLocation()
        }
    }

    private fun initializeViewModels() {
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
    }

    private fun initHelpersAndAdapter() {
        preferenceManagerHelper = PreferenceManagerHelper(this)
        locationHelper = LocationHelper(this)
        locationHelper.setLocationChangeListener(this)
        forecastAdapter = ForecastDataAdapter(emptyList())
        hourlyAdapter = HourlyDataAdapter(emptyList())
    }

    private fun initializeViews() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.forecastRv.layoutManager = LinearLayoutManager(this)
        binding.hourlyRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.swipeRefresh.setOnRefreshListener {
            binding.degreesTv.text = "---"
            locationHelper.updateCurrentLocation()
            binding.progressBar.visibility = View.VISIBLE
            binding.forecastRv.visibility = View.GONE
            binding.hourlyRv.visibility = View.GONE
            binding.additionalInfoLl.visibility = View.GONE
            Handler().postDelayed(Runnable {
                binding.swipeRefresh.isRefreshing = false
            }, 0)
        }
    }


    /**
     * Function to check if we need to fetch the location and the API data again
     * Cases where we fetch the location and data:
     * 1. No data was ever saved in the Saved Preferences
     * 2. 5 Minutes have passed since we last got the data
     */
    fun isNeedToUpdateData(restoreUpdateTime: Long) : Boolean{
        var retVal = false;

        var isMinutesPassed = TimeUtils.isMinutesPassed(restoreUpdateTime);

        retVal = restoreUpdateTime == 0L || isMinutesPassed

        return retVal;
    }


    /**
     * When location changes, we start the flow to update the data
     * This only gets called once per LocationHelper location updates request
     */
    override fun onLocationChanged(lon: Double, lat: Double) {

        if (locationHelper.isLocationAvailable()) {
            val lat : Double? = locationHelper.lat;
            val lon : Double? = locationHelper.lon;

            if (lat != null && lon != null) {
                updateCurrentWeather(lat, lon)
                updateForecastWeather(lat, lon)
                preferenceManagerHelper.saveUpdateTime()
            }

        } else {
            Log.e(TAG, "No location available")
        }
    }


    /**
     * Function to make API call and call the function to update forecast weather UI
     */
    private fun updateForecastWeather(lat: Double, lon: Double) {
        weatherViewModel.loadCurrentWeatherAndForecastByCoordinates(lat, lon)
            .enqueue(object : Callback<DailyWeatherAndForecastResponse> {
                override fun onResponse(
                    call: Call<DailyWeatherAndForecastResponse>,
                    response: Response<DailyWeatherAndForecastResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        var weatherData: DailyWeatherAndForecastResponse = response.body()!!
                        preferenceManagerHelper.saveForecastData(weatherData)
                        updateForecastDataUI(weatherData)

                    } else {
                        Log.e(TAG, response.message())
                    }
                }

                override fun onFailure(call: Call<DailyWeatherAndForecastResponse>, t: Throwable) {
                    Log.e(TAG, t.message.toString())

                }

            })
    }

    /**
     * Function to update the forecast weather UI (daily and hourly)
     */
    private fun updateForecastDataUI(weatherData: DailyWeatherAndForecastResponse) {
        var forecastDataList = GenericUtils.filterForecastData(weatherData.daily);
        binding.forecastRv.visibility = View.VISIBLE
        binding.hourlyRv.visibility = View.VISIBLE
        binding.additionalInfoLl.visibility = View.VISIBLE
        hourlyAdapter = HourlyDataAdapter(weatherData.hourly)
        forecastAdapter = ForecastDataAdapter(forecastDataList)
        binding.forecastRv.adapter = forecastAdapter;
        binding.hourlyRv.adapter = hourlyAdapter
        forecastAdapter.notifyDataSetChanged();
        hourlyAdapter.notifyDataSetChanged()
    }

    /**
     * Function to make API call and call the function to update current weather UI
     */
    private fun updateCurrentWeather(lat: Double, lon: Double) {
        weatherViewModel.loadCurrentWeatherByCoordinates(lat, lon)
            .enqueue(object : Callback<WeatherDataResponse> {
                override fun onResponse(
                    call: Call<WeatherDataResponse>,
                    response: Response<WeatherDataResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        var weatherData: WeatherDataResponse = response.body()!!
                        preferenceManagerHelper.saveWeatherData(weatherData);
                        updateCurrentWeatherUI(weatherData)

                    } else {
                        Log.e(TAG, "Failed to get data from API")
                    }
                }

                override fun onFailure(call: Call<WeatherDataResponse>, t: Throwable) {
                    Log.e(TAG, "Failed to get data from API")
                }

            })
    }


    /**
     * Function to update the current weather UI
     */
    private fun updateCurrentWeatherUI(weatherData: WeatherDataResponse) {

        binding.progressBar.visibility = View.GONE
        binding.additionalInfoLl.visibility = View.VISIBLE

        binding.currentCityTV.text = weatherData.cityName
        binding.degreesTv.text = String.format(
            WeatherStrings.WEATHER_WITH_DEGREES,
            (GenericUtils.kelvinToCelsius(weatherData.mainTemperatureData.temp)).toString()
        )
        binding.highDegreesTV.text = String.format(
            WeatherStrings.HIGH_WEATHER_WITH_DEGREES,
            (GenericUtils.kelvinToCelsius(weatherData.mainTemperatureData.tempMax)).toString()
        )
        binding.lowDegreesTv.text = String.format(
            WeatherStrings.LOW_WEATHER_WITH_DEGREES,
            (GenericUtils.kelvinToCelsius(weatherData.mainTemperatureData.tempMin)).toString()
        )
        binding.weatherConditionsTv.text =
            GenericUtils.capitalizeEveryWord(weatherData.weather[0].description)

        binding.humidityTv.text = String.format("%s%%",weatherData.mainTemperatureData.humidity)
        binding.windTv.text = String.format("%SMpH", weatherData.wind.speed);


        if (weatherData.rain != null) {
            binding.rainTv.text = String.format("%s%%",weatherData.rain.chanceOfRain);
        } else {
            binding.rainTv.text = getString(R.string.no_rain);
        }
;
    }


}