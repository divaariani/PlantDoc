package com.capstonebangkit.plantdoc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstonebangkit.plantdoc.ui.settings.SettingsPreference
import com.capstonebangkit.plantdoc.ui.settings.SettingsViewModel

class ViewModelFactory(private val pref: SettingsPreference) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}