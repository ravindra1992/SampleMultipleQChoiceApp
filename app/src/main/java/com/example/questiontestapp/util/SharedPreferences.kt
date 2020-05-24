package com.example.questiontestapp.util

import android.content.Context
import com.example.questiontestapp.R

class SharedPreferences (context: Context) {
    private val namePreferences = context.getString(R.string.sharedPreferencesName)
    private  val preferences = context.getSharedPreferences(namePreferences, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    fun addString(keyName : String, value : String) {
        editor.putString(keyName, value)
        editor.apply()
    }

    fun getString(keyName: String) : String {
        return preferences.getString(keyName, null).toString()
    }

    fun addBoolean(keyName: String, value : Boolean) {
        editor.putBoolean(keyName, value)
        editor.apply()
    }

    fun getBoolean(keyName: String) : Boolean {
        return preferences.getBoolean(keyName, false)
    }

    fun removeKey(keyName: String) {
        editor.remove(keyName)
        editor.apply()
    }

    fun verificKey(keyName: String) : Boolean {
        return preferences.contains(keyName)
    }

    fun destroy() {
        mInstance = null
    }

    companion object {
        private var mInstance : SharedPreferences? = null

        fun getInstance(context: Context) : SharedPreferences {
            if(mInstance == null)
                mInstance = SharedPreferences(context)
            return mInstance!!
        }
    }

}