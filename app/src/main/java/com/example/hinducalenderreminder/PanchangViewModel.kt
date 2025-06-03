package com.example.hinducalenderreminder

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PanchangViewModel : ViewModel() {

    private val _panchangData = MutableStateFlow<PanchangData?>(null)
    val panchangData: StateFlow<PanchangData?> = _panchangData

    init {
        fetchPanchang()
    }

    private fun fetchPanchang() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val api = RetrofitClient.instance
                Log.d("PanchangViewModel", "Fetching token...")

                val tokenResponse = api.getToken(
                    clientId = "882259b7-9c5f-4114-b86c-3b2cb6a2745e",
                    clientSecret = "6f8gpmxxaTD9hoIA4hALKYtXsh3Y4k2D7YqlMiGT"
                )
                if (tokenResponse.isSuccessful) {
                    Log.d("PanchangViewModel", "Token response: ${tokenResponse.body()}")
                } else {
                    Log.e("PanchangViewModel", "Failed to get token: ${tokenResponse.code()} ${tokenResponse.errorBody()?.string()}")
                    return@launch
                }
                val token = "Bearer ${tokenResponse.body()?.accessToken ?: return@launch}"

                val currentDateTime = java.time.ZonedDateTime.now(java.time.ZoneId.of("Asia/Kolkata"))
                val formattedDateTime = currentDateTime.format(
                    java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
                )
                val coords = "20.5937,78.9629"

                val response = api.getPanchangForToday(
                    datetime = formattedDateTime,
                    coordinates = coords,
                    bearerToken = token
                )
                Log.d("PanchangViewModel", "Received Panchang data: ${response.code()} ${response.body()}")

                // Move back to Main thread to update UI
                withContext(Dispatchers.Main) {
                    _panchangData.value = response.body()?.data
                }

            } catch (e: Exception) {
                Log.e("PanchangViewModel", "Failed to fetch Panchang: ${e.message}")
            }
        }
    }

}
