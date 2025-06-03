package com.example.hinducalenderreminder


import android.content.Context
import androidx.core.content.edit

/**
 * Manages Prokerala API token caching and validation.
 * Saves token using SharedPreferences and checks expiry before reuse.
 */
object TokenManager {
    private const val PREFS_NAME = "token_prefs"
    private const val KEY_TOKEN = "access_token"
    private const val KEY_EXPIRY = "expiry_time"

    /**
     * Saves the access token and expiry time in SharedPreferences.
     * @param context - Application context
     * @param token - Access token string
     * @param expiresIn - Duration in seconds until the token expires
     */
    fun saveToken(context: Context, token: String, expiresIn: Long) {
        val expiryTimeMillis = System.currentTimeMillis() + (expiresIn * 1000)
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            putString(KEY_TOKEN, token)
                .putLong(KEY_EXPIRY, expiryTimeMillis)
        }
    }

    /**
     * Returns a valid access token if not expired, else null.
     * @param context - Application context
     * @return cached token string if valid, otherwise null
     */
    fun getValidToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val token = prefs.getString(KEY_TOKEN, null)
        val expiryTime = prefs.getLong(KEY_EXPIRY, 0L)
        return if (System.currentTimeMillis() < expiryTime) token else null
    }

    /**
     * Clears the saved token (useful for debugging or logout).
     */
    fun clearToken(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit { clear() }
    }
}
