package com.abit8.geeksmentor.data

import android.content.Context

class Pref(private val context: Context) {
    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun isUserSeen(): Boolean{
        return pref.getBoolean(SEEN_KEY, false)
    }
    fun saveSeen(){
        pref.edit().putBoolean(SEEN_KEY, true).apply()
    }

    companion object{
        const val PREF_NAME = "Task.pref"
        const val SEEN_KEY = "seen.key"
    }
}