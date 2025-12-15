package com.example.practicofinal

import android.content.Context

class TokenManager(context: Context) {
    private val prefs = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString("TOKEN", token).apply()
    }

    fun getToken(): String? {
        return prefs.getString("TOKEN", null)
    }

    fun clearToken() {
        prefs.edit().remove("TOKEN").apply()
    }
}