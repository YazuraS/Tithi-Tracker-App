package com.example.hinducalenderreminder

import PanchangFileManager
import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

object DataDownloader {
    suspend fun downloadAndSavePanchangFile(
        context: Context,
        lat: Double,
        lon: Double,
        year: Int
    ): Boolean = withContext(Dispatchers.IO) {
        val urlString = "http://10.0.2.2:9000/v1/summary/coordinates/${lat}/${lon}/years/${year}?timezone=Asia/Calcutta"
        return@withContext try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.requestMethod = "GET"

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream.bufferedReader().use { it.readText() }
                PanchangFileManager.saveToFile(context, inputStream)
                true
            } else {
                Log.e("Downloader", "Failed to download file: ${connection.responseCode}")
                false
            }
        } catch (e: Exception) {
            Log.e("Downloader", "Exception during download: ${e.message}")
            false
        }
    }
}
