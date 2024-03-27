package com.example.weatherapponerobotixyossimeiri.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapponerobotixyossimeiri.BuildConfig
import com.example.weatherapponerobotixyossimeiri.R
import com.example.weatherapponerobotixyossimeiri.adapters.ForecastDataAdapter
import com.example.weatherapponerobotixyossimeiri.databinding.ActivityMainBinding
import com.example.weatherapponerobotixyossimeiri.models.DailyWeatherAndForecastResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import com.example.weatherapponerobotixyossimeiri.models.WeatherForecastResponse
import com.example.weatherapponerobotixyossimeiri.strings.WeatherStrings
import com.example.weatherapponerobotixyossimeiri.utils.GenericUtils
import com.example.weatherapponerobotixyossimeiri.utils.LocationHelper
import com.example.weatherapponerobotixyossimeiri.utils.WeatherCodeUtils
import com.example.weatherapponerobotixyossimeiri.viewmodels.WeatherViewModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), LocationHelper.LocationChangeListener {

    val TAG = "MainActivity"
    lateinit var locationHelper: LocationHelper;
    lateinit var binding: ActivityMainBinding
    private lateinit var weatherViewModel:WeatherViewModel
    private lateinit var forecastAdapter : ForecastDataAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        locationHelper = LocationHelper(this)
        locationHelper.setLocationChangeListener(this)
        locationHelper.updateCurrentLocation()
        weatherViewModel = WeatherViewModel()
        forecastAdapter = ForecastDataAdapter(emptyList())
        binding.forecastRv.layoutManager = LinearLayoutManager(this)


    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()


    }

    override fun onLocationChanged(lon: Double, lat: Double) {

        if (locationHelper.isLocationAvailable()) {
            val lat : Double? = locationHelper.lat;
            val lon : Double? = locationHelper.lon;

            if (lat != null && lon != null) {
                updateCurrentWeather(lat, lon)
                updateForecastWeather(lat, lon)
            }

        } else {
            Log.e(TAG, "No location available")
        }
    }

    private fun updateForecastWeather(lat: Double, lon: Double) {
        weatherViewModel.loadCurrentWeatherAndForecastByCoordinates(lat, lon)
            .enqueue(object : Callback<DailyWeatherAndForecastResponse> {
                override fun onResponse(
                    call: Call<DailyWeatherAndForecastResponse>,
                    response: Response<DailyWeatherAndForecastResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        var weatherData: DailyWeatherAndForecastResponse = response.body()!!
                        weatherViewModel.forecastWeatherData = weatherData;
                        var forecastDataList = GenericUtils.filterForecastData(weatherData.daily);
                        forecastAdapter = ForecastDataAdapter(forecastDataList);
                        forecastAdapter.notifyDataSetChanged();
                        binding.forecastRv.adapter = forecastAdapter;

                    } else {
                        Log.e(TAG, response.message())
                    }
                }

                override fun onFailure(call: Call<DailyWeatherAndForecastResponse>, t: Throwable) {
                    Log.e(TAG, t.message.toString())

                }

            })
    }

    private fun updateCurrentWeather(lat: Double, lon: Double) {
        weatherViewModel.loadCurrentWeatherByCoordinates(lat, lon)
            .enqueue(object : Callback<WeatherDataResponse> {
                override fun onResponse(
                    call: Call<WeatherDataResponse>,
                    response: Response<WeatherDataResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        var weatherData: WeatherDataResponse = response.body()!!
                        weatherViewModel.currentWeatherData = weatherData;
                        updateCurrentWeatherUI(weatherData)

                    } else {
                        binding.degreesTv.text = "Failed to get data from API";
                    }
                }

                override fun onFailure(call: Call<WeatherDataResponse>, t: Throwable) {
                    binding.degreesTv.text = "Failed to get data from API";
                }

            })
    }

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