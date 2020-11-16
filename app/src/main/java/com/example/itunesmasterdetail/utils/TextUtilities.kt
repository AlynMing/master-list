package com.example.itunesmasterdetail.utils

import android.util.Log

class TextUtilities {



    companion object {
        private const val TAG: String = "TextUtilities"

        @JvmStatic
        fun getLongFromString(value: String): Long {
            try {
                return value.toLong()
            } catch (e: NumberFormatException) {
                Log.d(TAG, TAG.plus(" Exception ").plus(e))
                return 0L
            }
        }
    }

}