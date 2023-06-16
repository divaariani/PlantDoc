package com.capstonebangkit.plantdoc

import android.content.Context
import android.content.SharedPreferences
import com.capstonebangkit.plantdoc.user.Constant.KEY_IS_LOGIN
import com.capstonebangkit.plantdoc.user.Constant.PREFS_NAME

class SessionManager(context: Context) {
    private var sharedPref: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()
    val isLogin = sharedPref.getBoolean(KEY_IS_LOGIN, false)

    fun setBooleanPref(prefBoolean: String, value: Boolean) {
        editor.putBoolean(prefBoolean, value)
        editor.apply()
    }
    fun setStringPref(prefString: String, value: String) {
        editor.putString(prefString, value)
        editor.apply()
    }
    fun clearData() {
        editor.clear().apply()
    }
}