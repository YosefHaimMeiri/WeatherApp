package com.example.weatherapponerobotixyossimeiri.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat

class LocationHelper(context: Context) : LocationListener {
    private val TAG = "LocationUtil"
    private var context: Context? = context
    private var locationManager: LocationManager? = null
    private var onLocationChangeListener: OnLocationChangeListener? = null


    interface OnLocationChangeListener {
        fun onLocationChanged(location: Location?)
    }

    fun requestLocationUpdates(listener: OnLocationChangeListener?) {
        onLocationChangeListener = listener
        locationManager = context!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager != null && ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        } else {
            Log.e(TAG, "Permission denied or LocationManager is null")
        }
    }


    fun removeLocationUpdates() {
        locationManager?.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location) {
        Log.d(TAG, "Latitude: " + location.latitude + ", Longitude: " + location.longitude)
        if (onLocationChangeListener != null) {
            onLocationChangeListener!!.onLocationChanged(location)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String) {}

    override fun onProviderDisabled(provider: String) {}
}