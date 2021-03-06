package com.example.basketbol1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
//import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.basketbol1.R
import com.example.basketbol1.Team
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import java.util.*

//import com.example.myapplication.R
//import com.example.myapplication.Team

private const val TAG = "MainActivity"
//private const val KEY_AScore = "AScore"
//private const val KEY_BScore = "BScore"
//private const val REQUEST_CODE_CLICKED = 0

class MainActivity : AppCompatActivity(), BBGameListFragment.Callbacks, main_fragment.MainCallbacks {

//    private lateinit var textA: TextView
//    private lateinit var textAPTS: TextView
//    private lateinit var textB: TextView
//    private lateinit var textBPTS: TextView
//    private lateinit var buttonAPT3: Button
//    private lateinit var buttonAPT2: Button
//    private lateinit var buttonAPT1: Button
//    private lateinit var buttonBPT3: Button
//    private lateinit var buttonBPT2: Button
//    private lateinit var buttonBPT1: Button
//    private lateinit var buttonReset: Button
//    private lateinit var displayButton : Button
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private var locationPermissionGranted = false
    private var lastKnownLocation : Location?= null
    private var long : Double = -71.798889
    private var lat : Double = 42.271389
//    private val basketbolViewModel: BasketbolViewModel by lazy {
//        ViewModelProviders.of(this).get(BasketbolViewModel::class.java)
//   }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        this.supportActionBar?.setDisplayShowTitleEnabled(false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (currentFragment == null) {
            val fragment = BBGameListFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment)
                .commit()
        }

//        Log.d(TAG, "Got a BasketbolViewModel: $basketbolViewModel")
//        buttonAPT3 = findViewById(R.id.buttonAPT3)
//        buttonAPT2 = findViewById(R.id.buttonAPT2)
//        buttonAPT1 = findViewById(R.id.buttonAPT1)
//        buttonBPT3 = findViewById(R.id.buttonBPT3)
//        buttonBPT2 = findViewById(R.id.buttonBPT2)
//        buttonBPT1 = findViewById(R.id.buttonBPT1)
//        buttonReset = findViewById(R.id.buttonReset)
//        displayButton = findViewById(R.id.displayButton)
//        textA = findViewById(R.id.textA)
//        textAPTS = findViewById(R.id.textAPTS)
//        textB = findViewById(R.id.textB)
//        textBPTS = findViewById(R.id.textBPTS)
//        val aScore = savedInstanceState?.getInt(KEY_AScore, 0) ?: 0
//        basketbolViewModel.teams[0].score = aScore
//        textAPTS.text = basketbolViewModel.teamAPoints.toString()
//        val bScore = savedInstanceState?.getInt(KEY_BScore, 0) ?: 0
//        basketbolViewModel.teams[1].score = bScore
//        textBPTS.text = basketbolViewModel.teamBPoints.toString()
//        buttonAPT3.setOnClickListener {
//            basketbolViewModel.updatePts("A", 3)
//            textAPTS.text = basketbolViewModel.teamAPoints.toString()
//        }
//        buttonAPT2.setOnClickListener {
//            basketbolViewModel.updatePts("A", 2)
//            textAPTS.text = basketbolViewModel.teamAPoints.toString()
//        }
//        buttonAPT1.setOnClickListener {
//            basketbolViewModel.updatePts("A", 1)
//            textAPTS.text = basketbolViewModel.teamAPoints.toString()
//        }
//        buttonBPT3.setOnClickListener {
//            basketbolViewModel.updatePts("B", 3)
//            textBPTS.text = basketbolViewModel.teamBPoints.toString()
//        }
//        buttonBPT2.setOnClickListener {
//            basketbolViewModel.updatePts("B", 2)
//            textBPTS.text = basketbolViewModel.teamBPoints.toString()
//        }
//        buttonBPT1.setOnClickListener {
//            basketbolViewModel.updatePts("B", 1)
//            textBPTS.text = basketbolViewModel.teamBPoints.toString()
//        }
//
//        buttonReset.setOnClickListener {
//            basketbolViewModel.reset()
//            textAPTS.text = basketbolViewModel.teamAPoints.toString()
//            textBPTS.text = basketbolViewModel.teamBPoints.toString()
//        }
//
//        displayButton.setOnClickListener {
//            val isClicked = basketbolViewModel.butIsClicked
//            val intent = BASKETBOL2.newIntent(this@MainActivity,basketbolViewModel.butIsClicked)
//            startActivityForResult(intent, REQUEST_CODE_CLICKED)
//
//        }
        getLocationPermission()
        getDeviceLocation()


    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    private fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationClient.lastLocation
                locationResult.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            LatLng(lastKnownLocation!!.latitude,
                                lastKnownLocation!!.longitude)
                            lat = lastKnownLocation!!.latitude
                            long = lastKnownLocation!!.longitude
                            Log.d(TAG, "Current location " + lat + " " + long)
                        }
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.")
                        Log.e(TAG, "Exception: %s", task.exception)
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    override fun onGameSelected(bbgameID: UUID) {
        Log.d(TAG, "MainActivity.onGameSelected: $bbgameID")
        val fragment = main_fragment.newInstance(bbgameID, lat, long)
        this.supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }

    override fun onDisplay(teamAWin: Boolean) {
        this.supportActionBar?.show()
        this.supportActionBar?.setDisplayShowTitleEnabled(false)
        val fragment = BBGameListFragment.newInstance(teamAWin)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }

//    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?) {
//      super.onActivityResult(requestCode, resultCode, data)
//        if(resultCode != Activity.RESULT_OK)
//            {return}
//        if(requestCode == REQUEST_CODE_CLICKED){
//            basketbolViewModel.butIsClicked = data?.getBooleanExtra(IS_CLICKED_TWO, false)?:false
//            val resultClick = when {
//                basketbolViewModel.butIsClicked -> R.string.clickB
//                else -> R.string.clickA
//            }
//            displayButton.setText(resultClick)
//            Log.d(TAG, "updated button on homepage");
//        }
//    }
//
//
//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "onStart() called")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "onPause() called")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "onResume() called")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy() called")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "onStop() called")
//    }
//
//        override fun onSaveInstanceState(savedInstanceState: Bundle) {
//            super.onSaveInstanceState(savedInstanceState)
//            Log.i(TAG, "onSavedInstanceState")
//            savedInstanceState.putInt(KEY_AScore, basketbolViewModel.teams[0].score)
//            savedInstanceState.putInt(KEY_BScore, basketbolViewModel.teams[1].score)
//
//        }
    companion object{
    private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }
}

