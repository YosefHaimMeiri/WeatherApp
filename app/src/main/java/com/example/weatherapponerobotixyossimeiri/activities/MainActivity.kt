package com.example.weatherapponerobotixyossimeiri.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapponerobotixyossimeiri.BuildConfig
import com.example.weatherapponerobotixyossimeiri.R
import com.example.weatherapponerobotixyossimeiri.utils.LocationHelper

class MainActivity : AppCompatActivity() {

    val API_KEY = BuildConfig.OPENWEATHER_API_KEY;
    lateinit var locationHelper: LocationHelper;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        locationHelper = LocationHelper(this);
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        // TODO: Handle location getting + API Requests here
    }

}