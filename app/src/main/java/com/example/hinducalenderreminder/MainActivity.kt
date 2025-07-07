package com.example.hinducalenderreminder

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresPermission
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.launch
import java.time.LocalDate


class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val viewmodel: PanchangViewModel by viewModels()

        // Register permission launcher
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.d("Panchang", "Location permission granted")
                fetchLocationAndLoadData(viewmodel)
            } else {
                Log.e("Panchang", "Location permission denied")
            }
        }

        // Check location permission
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fetchLocationAndLoadData(viewmodel)
        } else {
            locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        setContent {
            MaterialTheme {
                HomeScreen()
            }
        }
    }

//    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
//    private fun getUserLocationAndFetchPanchang() {
//
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            Log.e("Panchang", "Permission still not granted")
//            return
//        }
//
//        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//            if (location != null) {
//                val latitude = String.format("%.2f", location.latitude).toDouble()
//                val longitude = String.format("%.2f", location.longitude).toDouble()
//                val year = LocalDate.now().year
//
//                Log.d("Location", "Lat: $latitude, Lon: $longitude")
//
//                lifecycleScope.launch {
//                    val fileName = PanchangFileManager.FILE_NAME
//
//                    if (!PanchangFileManager.isFileAlreadySaved(applicationContext, fileName)) {
//                        Log.d("PanchangData", "File not found, downloading...")
//
//                        val success = DataDownloader.downloadAndSavePanchangFile(
//                            applicationContext,
//                            latitude,
//                            longitude,
//                            year
//                        )
//
//                        if (success) {
//                            val todayData = PanchangFileManager.getTodayData(applicationContext)
//                            Log.d("PanchangData", todayData.toString())
//                        } else {
//                            Log.e("PanchangData", "Download failed.")
//                        }
//                    } else {
//                        Log.d("PanchangData", "File already exists, skipping download.")
//                    }
//                }
//            } else {
//                Log.e("Location", "Location is null")
//            }
//        }.addOnFailureListener {
//            Log.e("Location", "Failed to get location", it)
//        }
//    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun fetchLocationAndLoadData(viewModel: PanchangViewModel) {

        val cancellationTokenSource = CancellationTokenSource()

        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cancellationTokenSource.token
        ).addOnSuccessListener { location ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude
                val currentYear = LocalDate.now().year

                Log.d("Location", "Lat: $latitude, Long: $longitude, Year: $currentYear")

                val fileName = PanchangFileManager.FILE_NAME

                lifecycleScope.launch {
                    if (!PanchangFileManager.isFileAlreadySaved(applicationContext, fileName)) {
                        Log.d("PanchangData", "File not found, downloading...")
                        val success = DataDownloader.downloadAndSavePanchangFile(
                            applicationContext,
                            latitude,
                            longitude,
                            currentYear
                        )

                        if (success) {
                            Log.d("PanchangData", "Download success!")
                        } else {
                            Log.e("PanchangData", "Download failed.")
                        }
                    } else {
                        Log.d("PanchangData", "File already exists, skipping download.")
                    }

                    // Load today's Panchang
                    val todayData = PanchangFileManager.getTodayData(applicationContext)

                    viewModel.loadTodayData(applicationContext)
                    Log.d("PanchangData", todayData.toString())
                }

            } else {
                Log.e("Location", "Location is null")
            }
        }.addOnFailureListener {
            Log.e("Location", "Failed to get current location", it)
        }
    }

}
