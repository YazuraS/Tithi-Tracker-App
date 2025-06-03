package com.example.hinducalenderreminder

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@Composable
fun PanchangCard(data: PanchangData) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Tithi: ${data.tithi.firstOrNull()?.name}", style = MaterialTheme.typography.titleLarge)
            Text("Nakshatra: ${data.nakshatra.firstOrNull()?.name}", style = MaterialTheme.typography.bodyMedium)
            Text("Yoga: ${data.yoga.firstOrNull()?.name}", style = MaterialTheme.typography.bodyMedium)
            Text("Karana: ${data.karana.firstOrNull()?.name}", style = MaterialTheme.typography.bodyMedium)
            Text("Sunrise: ${ZonedDateTime.parse(data.sunrise).format(DateTimeFormatter.ofPattern("hh:mm a"))
            }", style = MaterialTheme.typography.bodyMedium)
            Text("Sunset: ${ZonedDateTime.parse(data.sunset).format(DateTimeFormatter.ofPattern("hh:mm a"))
            }", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

