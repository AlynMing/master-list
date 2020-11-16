package com.example.itunesmasterdetail

import android.content.SharedPreferences
import com.example.itunesmasterdetail.utils.Constants

class SaveDataPreference(private val sharedPreferences: SharedPreferences) {

     fun setLastViewedDate(date: String) {
            setSharedPreference(Constants.LAST_DATE_VIEWED, date)
     }

    fun getLastViewedDate(): String {
        return sharedPreferences.getString(Constants.LAST_DATE_VIEWED, Constants.NO_DATE)!!
    }

    private fun setSharedPreference(key: String, value: String) {
        getEditor().putString(key, value).apply()
    }

     private fun getEditor(): SharedPreferences.Editor {
         return sharedPreferences.edit()
     }

}