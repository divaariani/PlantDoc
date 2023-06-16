package com.capstonebangkit.plantdoc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capstonebangkit.plantdoc.databinding.ActivityMainBinding
import com.capstonebangkit.plantdoc.ui.settings.SettingsActivity
import com.capstonebangkit.plantdoc.ui.settings.SettingsPreference
import com.capstonebangkit.plantdoc.ui.settings.SettingsViewModel
import com.capstonebangkit.plantdoc.ui.settings.ViewModelFactory
import com.capstonebangkit.plantdoc.user.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: SettingsPreference
    private lateinit var sharedPref: SessionManager
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_pupuk, R.id.navigation_bookmark
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        pref = SettingsPreference.getInstance(dataStore)
        val themeViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(SettingsViewModel::class.java)
        themeViewModel.getThemeSettings().observe(this) { isDarkModeActivate ->
            if (isDarkModeActivate){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        sharedPref = SessionManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            /*
            R.id.search -> {
                Intent(this, SearchActivity::class.java).also {
                    startActivity(it)
                }
            }
            */
            R.id.settings -> {
                Intent(this, SettingsActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.action_logout -> {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Log Out?")
                    ?.setPositiveButton("Yes") { _, _ ->
                        sharedPref.clearData()
                        val intent = Intent(this, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                        finish()
                    }
                    ?.setNegativeButton("Cancel", null)
                val alert = alertDialog.create()
                alert.show()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}