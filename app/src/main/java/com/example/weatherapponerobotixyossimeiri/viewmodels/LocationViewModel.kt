package com.example.weatherapponerobotixyossimeiri.viewmodels

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LocationViewModel() : ViewModel(), Parcelable {
    private val _latitude = MutableLiveData<Double>()
    val latitude: LiveData<Double> = _latitude

    private val _longitude = MutableLiveData<Double>()
    val longitude: LiveData<Double> = _longitude

    constructor(parcel: Parcel) : this() {
        _latitude.value = parcel.readDouble()
        _longitude.value = parcel.readDouble()
    }

    fun setLocation(lat: Double, lon: Double) {
        _latitude.value = lat
        _longitude.value = lon
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(_latitude.value ?: 0.0)
        parcel.writeDouble(_longitude.value ?: 0.0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LocationViewModel> {
        override fun createFromParcel(parcel: Parcel): LocationViewModel {
            return LocationViewModel(parcel)
        }

        override fun newArray(size: Int): Array<LocationViewModel?> {
            return arrayOfNulls(size)
        }
    }
}
