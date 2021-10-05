package com.example.basketbol1

import android.Manifest
import android.location.LocationManager
import androidx.core.app.ActivityCompat

import android.app.Activity
import android.content.Context
import androidx.constraintlayout.motion.widget.Debug.getLocation
import android.widget.Toast

import android.content.pm.PackageManager
import android.location.Location
import android.content.DialogInterface

import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AlertDialog


class GPSUtils {
    private val REQUEST_LOCATION = 1
    private val instance = GPSUtils()
    private var locationManager: LocationManager? = null
    private var latitude: String? = null 
    private  var longitude: String? = null
    
    private open fun GPSUtils() {
        //stuff and things
    }

    fun getInstance(): GPSUtils? {
        return instance
    }

    fun initPermissions(activity: Activity?) {
        ActivityCompat.requestPermissions(
            activity!!,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION
        )
    }

    fun findDeviceLocation(activity: Activity) {
        locationManager =
            activity.getSystemService<Any>(Context.LOCATION_SERVICE) as LocationManager

        //Check gps is enable or not
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //Write Function To enable gps
            gpsEnable(activity)
        } else {
            //GPS is already On then
            getLocation(activity)
        }
    }

    private fun getLocation(activity: Activity) {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION
            )
        } else {
            val location: Location? =
                locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val LocationNetwork: Location? =
                locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            val LocationPassive: Location? =
                locationManager!!.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
            if (location != null) {
                val lat: Double = location.getLatitude()
                val long: Double = location.getLongitude()
                latitude = lat.toString()
                longitude = long.toString()
            } else if (LocationNetwork != null) {
                val lat: Double = LocationNetwork.getLatitude()
                val long: Double = LocationNetwork.getLongitude()
                latitude = lat.toString()
                longitude = long.toString()
            } else if (LocationPassive != null) {
                val lat: Double = LocationPassive.getLatitude()
                val long: Double = LocationPassive.getLongitude()
                latitude = lat.toString()
                longitude = long.toString()
            } else {
                Toast.makeText(activity, "Can't Get Your Location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun gpsEnable(activity: Activity) {
        val builder: AlertDialog.Builder = Builder(activity)
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES",
            DialogInterface.OnClickListener { dialog, which ->
                activity.startActivity(
                    Intent(
                        Settings.ACTION_LOCATION_SOURCE_SETTINGS
                    )
                )
            }).setNegativeButton("NO",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    fun getLatitude(): String? {
        return latitude
    }

    fun setLatitude(latitude: String?) {
        this.latitude = latitude
    }

    fun getLongitude(): String? {
        return longitude
    }

    FUCK THIS

    fun setLongitude(longitude: String?) {
        this.longitude = longitude
    }
}