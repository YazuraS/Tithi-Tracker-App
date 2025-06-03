package com.example.hinducalenderreminder

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(viewModel: PanchangViewModel = viewModel()) {
    val data by viewModel.panchangData.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Text("Today's Panchang", style = MaterialTheme.typography.headlineMedium)
        LaunchedEffect(data) {
            Log.d("UI_CHECK", "PanchangData: $data")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (data != null) {
            PanchangCard(data!!)
        } else {
            Text("Fetching Panchang...", modifier = Modifier.padding(top = 8.dp))
        }
    }
}


@Preview(showBackground = true, name = "Homescreen")
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
