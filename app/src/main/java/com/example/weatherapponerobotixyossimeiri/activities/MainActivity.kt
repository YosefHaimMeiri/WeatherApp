package com.example.weatherapponerobotixyossimeiri.activities

import android.location.LocationListener
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapponerobotixyossimeiri.BuildConfig
import com.example.weatherapponerobotixyossimeiri.R
import com.example.weatherapponerobotixyossimeiri.databinding.ActivityMainBinding
import com.example.weatherapponerobotixyossimeiri.models.WeatherData
import com.example.weatherapponerobotixyossimeiri.utils.GenericUtils
import com.example.weatherapponerobotixyossimeiri.utils.LocationHelper
import com.example.weatherapponerobotixyossimeiri.viewmodels.WeatherViewModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

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
        locationHelper.updateCurrentLocation()
        locationHelper.setLocationChangeListener(this)
        weatherViewModel = WeatherViewModel()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()


    }

    override fun onLocationChanged(lon: Double, lat: Double) {
        binding.latTv.text = lat.toString();
        binding.lonTv.text = lon.toString();

        // TODO: Handle location getting + API Requests here
        binding.progressBar.visibility = View.VISIBLE;
        if (locationHelper.isLocationAvailable()) {
            val lat : Double? = locationHelper.lat;
            val lon : Double? = locationHelper.lon;

            if (lat != null && lon != null) {
                weatherViewModel.loadCurrentWeatherByCoordinates(lat,lon).enqueue(object : retrofit2.Callback<WeatherData> {
                    override fun onResponse(
                        call: Call<WeatherData>,
                        response: Response<WeatherData>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            var weatherData : WeatherData = response.body()!!
                            binding.cityTv.text = weatherData.cityName
                            binding.degreesTV.text = (GenericUtils.kelvinToCelsius(weatherData.mainTemperatureData.temp)).toString() + "°";

                        } else {
                            binding.degreesTV.text = "Failed to get data from API";
                        }
                    }

                    override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                        binding.degreesTV.text = "Failed to get data from API";
                    }

                })
            } else {
                // Shouldn't ever get here
            }

        } else {
            // TODO: Log error
        }

        binding.progressBar.visibility = View.GONE;
    }


}