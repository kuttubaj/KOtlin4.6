package com.example.kotlin46

import android.content.Context

class PreferenseHelper(context: Context) {

    private val preferenseHelper = context.getSharedPreferences("My pref", Context.MODE_PRIVATE)

    var isHasPermission: Boolean
        get() = preferenseHelper.getBoolean(IS_HAS_PERMISSION_KEY, false)
        set(value){
            preferenseHelper.edit().putBoolean(IS_HAS_PERMISSION_KEY, value).apply()
        }


    companion object{
        private val IS_HAS_PERMISSION_KEY = "permision"
    }
}