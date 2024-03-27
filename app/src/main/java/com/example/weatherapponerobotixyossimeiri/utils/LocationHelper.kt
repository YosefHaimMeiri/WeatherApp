package com.example.weatherapponerobotixyossimeiri.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationHelper(private val context : Context)  {
    private val TAG = "LocationUtil"
    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
    private var locationChangeListener: LocationChangeListener? = null

    var lon : Double? = null
        private set
    var lat : Double? = null
        private set


    /**
     * Interface for location change listener - To be used with the MainActivity
     */
    interface LocationChangeListener {
        fun onLocationChanged(lon: Double, lat: Double)
    }


    fun setLocationChangeListener(listener: LocationChangeListener) {
        this.locationChangeListener = listener
    }

    private val locationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        interval = 10000 // 10 seconds
    }
    /**
     * Attempt to get the current location - might return null if FusedLocationProviderClient couldn't retrieve it
     * Once location is retrieved, the callback in MainActivity is triggered, which in turn triggers the API fetching
     */
    fun updateCurrentLocation() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
            Log.d(TAG, "Location permission requested")
            updateCurrentLocation();
        } else {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,null)
            Log.d(TAG, "Location updates requested")
        }
    }

    /**
     * LocationCallback - Will stop asking for location updates once we got our location
     */
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult?.lastLocation?.let {
                lon = it.longitude
                lat = it.latitude
                locationChangeListener?.onLocationChanged(lon!!, lat!!);
                stopLocationUpdates();
            }
        }
    }
    fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    fun isLocationAvailable() : Boolean {
        return lon != null && lat != null;
    }




}