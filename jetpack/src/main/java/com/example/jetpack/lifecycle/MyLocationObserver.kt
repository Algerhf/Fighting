package com.example.jetpack.lifecycle

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationListenerCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyLocationObserver(val context: Context) : DefaultLifecycleObserver {

    companion object {
        var TAG: String = MyLocationObserver::class.java.simpleName
    }

    private lateinit var mLocationManager: LocationManager
    private val mMyLocationListener by lazy {
        MyLocationListener()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        mLocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000L,1f,mMyLocationListener)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        mLocationManager.removeUpdates(mMyLocationListener)
    }

    inner class MyLocationListener : LocationListenerCompat {
        override fun onLocationChanged(location: Location) {
            Log.e(TAG, "hf onLocationChanged  ${location.longitude}  ${location.latitude}")
        }
    }
}