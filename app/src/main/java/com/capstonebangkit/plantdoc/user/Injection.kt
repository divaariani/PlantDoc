package com.capstonebangkit.plantdoc.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.capstonebangkit.plantdoc.SessionManager
import com.capstonebangkit.plantdoc.api.ApiConfig
import com.capstonebangkit.plantdoc.user.Constant

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constant.PREFS_NAME)
object Injection {
    fun provideRepository(context: Context): UserRepository {
        val sharedPref = SessionManager(context)
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(sharedPref, apiService)
    }
}