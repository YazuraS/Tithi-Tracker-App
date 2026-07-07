package com.example.hinducalenderreminder

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
        }
    }
}

