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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun PanchangCard() {
    var panchangData by remember { mutableStateOf<PanchangData?>(null) }

    val currentDateTime = java.time.ZonedDateTime.now(java.time.ZoneId.of("Asia/Kolkata"))
    val formattedDateTime = currentDateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"))
    val coords = "20.5937,78.9629"

    LaunchedEffect(Unit) {
        val tokenCall = withContext(Dispatchers.IO) {
            RetrofitClient.instance.getToken(
                clientId = "882259b7-9c5f-4114-b86c-3b2cb6a2745e",
                clientSecret = "6f8gpmxxaTD9hoIA4hALKYtXsh3Y4k2D7YqlMiGT",
            )
        }

        if (tokenCall.isSuccessful) {
            val accessToken = tokenCall.body()?.accessToken
            Log.d("API", "Token received: $accessToken")

            val panchangResponse = withContext(Dispatchers.IO) {
                RetrofitClient.instance.getPanchangForToday(
                    bearerToken = "Bearer $accessToken",
                    coordinates = coords,
                    datetime = formattedDateTime,
                    language = "en",
                    ayanamsa = 1
                )
            }
            if (panchangResponse.isSuccessful) {
                panchangData = panchangResponse.body()?.data
                Log.d("API", "Panchang fetched successfully")

            } else {
                Log.e("API", "Panchang fetch failed: ${panchangResponse.code()} ${panchangResponse.errorBody()?.string()}")

            }

        }
        else {
            Log.e("API", "Token fetch failed: ${tokenCall.body()} ${tokenCall.errorBody()?.string()}")
        }


    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("🌞 Panchang Today", style = MaterialTheme.typography.titleLarge)

            if (panchangData != null) {
                Text("Sunrise: ${panchangData!!.sunrise}")
                Text("Sunset: ${panchangData!!.sunset}")
                Text("Tithi: ${panchangData!!.tithi.firstOrNull()?.name}")
                Text("Nakshatra: ${panchangData!!.nakshatra.firstOrNull()?.name}")
            } else {
                Text("Loading...", color = Color.Gray)
            }
        }
    }
}


