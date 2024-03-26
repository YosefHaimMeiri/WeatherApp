package com.example.weatherapponerobotixyossimeiri.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapponerobotixyossimeiri.R
import com.example.weatherapponerobotixyossimeiri.databinding.ActivityMainBinding
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

    lateinit var locationHelper: LocationHelper;
    lateinit var binding: ActivityMainBinding
    private lateinit var weatherViewModel:WeatherViewModel


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
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()


    }

    override fun onLocationChanged(lon: Double, lat: Double) {

        // TODO: Handle location getting + API Requests here
        if (locationHelper.isLocationAvailable()) {
            val lat : Double? = locationHelper.lat;
            val lon : Double? = locationHelper.lon;

            if (lat != null && lon != null) {
                updateCurrentWeather(lat, lon)
                updateForecastWeather(lat, lon)
            } else {
                // Shouldn't ever get here
            }

        } else {
            // TODO: Log error
        }
    }

    private fun updateForecastWeather(lat: Double, lon: Double) {
        weatherViewModel.loadForecastByCoordinates(lat, lon)
            .enqueue(object : Callback<WeatherForecastResponse> {
                override fun onResponse(
                    call: Call<WeatherForecastResponse>,
                    response: Response<WeatherForecastResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        var weatherData: WeatherForecastResponse = response.body()!!

                    } else {
                    }
                }

                override fun onFailure(call: Call<WeatherForecastResponse>, t: Throwable) {

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


                        binding.progressBar.visibility = View.GONE
                        binding.currentCityTV.text = weatherData.cityName
                        binding.degreesTv.text = String.format(WeatherStrings.weatherWithDegrees, (GenericUtils.kelvinToCelsius(weatherData.mainTemperatureData.temp)).toString())
                        binding.highDegreesTV.text = String.format(WeatherStrings.highWeatherWithDegrees,  (GenericUtils.kelvinToCelsius(weatherData.mainTemperatureData.tempMax)).toString())
                        binding.lowDegreesTv.text = String.format(WeatherStrings.lowWeatherWithDegrees,  (GenericUtils.kelvinToCelsius(weatherData.mainTemperatureData.tempMin)).toString())
                        binding.weatherConditionsTv.text = GenericUtils.capitalizeEveryWord(weatherData.weather[0].description)


                        fetchAndUpdateWeatherIcon(weatherData);

                    } else {
                        binding.degreesTv.text = "Failed to get data from API";
                    }
                }

                override fun onFailure(call: Call<WeatherDataResponse>, t: Throwable) {
                    binding.degreesTv.text = "Failed to get data from API";
                }

            })
    }

    private fun fetchAndUpdateWeatherIcon(weatherData: WeatherDataResponse) {
        val iconUrl : String = String.format(WeatherCodeUtils.iconUrlPlaceholder, weatherData.weather[0].icon);

//        Picasso.get().load(iconUrl).into(binding.weatherIv)
    }

}