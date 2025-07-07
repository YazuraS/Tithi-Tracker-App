package com.example.hinducalenderreminder

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@Composable
fun PanchangCard(data: PanchangEntry) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

                Text("TITHI: ${data.tithi.uppercase()}", fontSize = 15.sp, fontWeight = SemiBold)
                Spacer(modifier = Modifier.height(15.dp))

            // 2x2 Grid using two Rows


                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Sunrise: ${data.sunrise}")
                    Text("Moonsrise: ${data.moonrise}")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Sunset: ${data.sunset}")
                    Text("Moonset: ${data.moonset}")
                }
//                Text("Sunrise: ${data.sunrise}")
//                Text("Sunset: ${data.sunset}")
//                Text("Moonrise: ${data.moonrise}")
//                Text("Moonset: ${data.moonset}")

//            Text("Tithi: ${data.tithi.firstOrNull()?.name}", style = MaterialTheme.typography.titleLarge)
//            Text("Nakshatra: ${data.nakshatra.firstOrNull()?.name}", style = MaterialTheme.typography.bodyMedium)
//            Text("Yoga: ${data.yoga.firstOrNull()?.name}", style = MaterialTheme.typography.bodyMedium)
//            Text("Karana: ${data.karana.firstOrNull()?.name}", style = MaterialTheme.typography.bodyMedium)
//            Text("Sunrise: ${ZonedDateTime.parse(data.sunrise).format(DateTimeFormatter.ofPattern("hh:mm a"))
//            }", style = MaterialTheme.typography.bodyMedium)
//            Text("Sunset: ${ZonedDateTime.parse(data.sunset).format(DateTimeFormatter.ofPattern("hh:mm a"))
//            }", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

