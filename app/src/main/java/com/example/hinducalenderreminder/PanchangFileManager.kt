import android.content.Context
import com.example.hinducalenderreminder.PanchangEntry
import com.example.hinducalenderreminder.PanchangResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

// PanchangFileHelper.kt
object PanchangFileManager {
    const val FILE_NAME = "panchang_2025.json"

    fun saveToFile(context: Context, json: String) {
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }

    fun readFromFile(context: Context): String? {
        return try {
            context.openFileInput(FILE_NAME).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            null
        }
    }

    fun getTodayData(context: Context): PanchangEntry? {
        val json = readFromFile(context) ?: return null
        val gson = Gson()
        val response = gson.fromJson(json, PanchangResponse::class.java) // Use correct top-level class

        val yearlyData = response.data

        val today = LocalDate.now()
        val todayString = today.toString() // "2025-06-20"
        val monthName = today.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH) // e.g. "June"

        val monthList = yearlyData[monthName] ?: return null
        return monthList.find { it.date == todayString }
    }


    fun isFileAlreadySaved(context: Context, fileName: String): Boolean {
        val file = File(context.filesDir, fileName)
        return file.exists()
    }
}
