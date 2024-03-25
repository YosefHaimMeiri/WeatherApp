package com.example.weatherapponerobotixyossimeiri.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

class LocationHelper(private val context : Context)  {
    private val TAG = "LocationUtil"
    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

    var lon : Double? = null
        private set
    var lat : Double? = null
        private set

    /**
     * Attempt to get the current location - might return null if FusedLocationProviderClient couldn't retrieve it
     */
    fun updateCurrentLocation(context : Context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
            updateCurrentLocation(context);
        } else {
            fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null).addOnSuccessListener {
                if (it != null) {
                    lon = it.longitude;
                    lat = it.latitude
                }
            }
        }
    }

    fun isLocationAvailable() : Boolean {
        return lon != null && lat != null;
    }




}