package com.example.newsy

import android.content.Context

object Shared {
    private const val PREFS_NAME = "news_app_prefs"
    private const val KEY_COUNTRY = "selected_country"

    fun saveCountry(context: Context, countryCode: String) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_COUNTRY, countryCode).apply()
    }

    fun getSavedCountry(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_COUNTRY, null)
    }
}

