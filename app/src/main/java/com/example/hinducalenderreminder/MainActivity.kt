package com.example.hinducalenderreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hinducalenderreminder.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MaterialTheme {
                HomeScreen()
                PanchangCard()
            }
        }
        //val navView: BottomNavigationView = binding.navView

        //val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //val appBarConfiguration = AppBarConfiguration(
          //  setOf(
          //      R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
          //  )
        //)
        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)
    }


}