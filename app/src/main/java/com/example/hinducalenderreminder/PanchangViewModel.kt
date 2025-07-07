package com.example.hinducalenderreminder

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//class PanchangViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val _panchangData = MutableStateFlow<PanchangData?>(null)
//    @SuppressLint("StaticFieldLeak")
//    private val context = getApplication<Application>().applicationContext
//
//
//    val panchangData: StateFlow<PanchangData?> = _panchangData
//
//    init {
//        fetchPanchang()
//    }
//
//    private fun fetchPanchang() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val api = RetrofitClient.instance
//                Log.d("PanchangViewModel", "Fetching token...")
//                var token = TokenManager.getValidToken(context)
//
//                if (token == null) {
//                    val tokenResponse = api.getToken(
//                        clientId = "882259b7-9c5f-4114-b86c-3b2cb6a2745e",
//                        clientSecret = "6f8gpmxxaTD9hoIA4hALKYtXsh3Y4k2D7YqlMiGT"
//                    )
//                    if (tokenResponse.isSuccessful) {
//                        Log.d("PanchangViewModel", "Token response: ${tokenResponse.body()}")
//                        token = "Bearer ${tokenResponse.body()?.accessToken ?: return@launch}"
//                        TokenManager.saveToken(context,token,3600L)
//                    } else {
//                        Log.e("PanchangViewModel", "Failed to get token: ${tokenResponse.code()} ${tokenResponse.errorBody()?.string()}")
//                        return@launch
//                    }
//                }
//
//
//                val currentDateTime = java.time.ZonedDateTime.now(java.time.ZoneId.of("Asia/Kolkata"))
//                val formattedDateTime = currentDateTime.format(
//                    java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
//                )
//                val coords = "20.5937,78.9629"
//
//                val response = api.getPanchangForToday(
//                    datetime = formattedDateTime,
//                    coordinates = coords,
//                    bearerToken = token
//                )
//                Log.d("PanchangViewModel", "Received Panchang data: ${response.code()} ${response.body()}")
//
//                // Move back to Main thread to update UI
//                withContext(Dispatchers.Main) {
//                    _panchangData.value = response.body()?.data
//                }
//
//            } catch (e: Exception) {
//                Log.e("PanchangViewModel", "Failed to fetch Panchang: ${e.message}")
//            }
//        }
//    }
//
//}


class PanchangViewModel(application: Application) : AndroidViewModel(application) {

    private val _todayPanchang = MutableStateFlow<PanchangEntry?>(null)
    val todayPanchang: StateFlow<PanchangEntry?> = _todayPanchang

    fun loadTodayData(context: Context) {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO){
                PanchangFileManager.getTodayData(context)
            }
            _todayPanchang.value = data
            Log.d("PanchangViewModel", "Loaded Panchang: $data")

        }
    }
}
