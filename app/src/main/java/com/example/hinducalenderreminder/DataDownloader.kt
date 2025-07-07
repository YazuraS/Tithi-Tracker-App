package com.example.hinducalenderreminder

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
//
//object DataDownloader {
//
//    private const val TAG = "PanchangDataDownloader"
//
//    suspend fun downloadAndSaveJson(context: Context, fileUrl: String): Boolean {
//        return withContext(Dispatchers.IO) {
//            try {
//                val url = URL(fileUrl)
//                val connection = url.openConnection() as HttpURLConnection
//                connection.connectTimeout = 10000
//                connection.readTimeout = 15000
//                connection.requestMethod = "GET"
//                connection.connect()
//
//                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
//                    val inputStream: InputStream = BufferedInputStream(connection.inputStream)
//                    val json = inputStream.bufferedReader().use { it.readText() }
//
//                    PanchangFileManager.saveToFile(context, json)
//                    Log.d(TAG, "File downloaded and saved successfully.")
//                    true
//                } else {
//                    Log.e(TAG, "Download failed with response code: ${connection.responseCode}")
//                    false
//                }
//            } catch (e: Exception) {
//                Log.e(TAG, "Exception during download: ${e.message}", e)
//                false
//            }
//        }
//    }
//}


object DataDownloader {

    ///v1/summary/coordinates/<string:latitude>/<string:longitude>/years/<string:year>
    //http://192.168.1.11:9000/

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
