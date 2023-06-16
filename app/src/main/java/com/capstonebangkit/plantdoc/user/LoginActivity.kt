package com.capstonebangkit.plantdoc.user

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstonebangkit.plantdoc.MainActivity
import com.capstonebangkit.plantdoc.databinding.ActivityLoginBinding
import com.capstonebangkit.plantdoc.user.Constant.KEY_IS_LOGIN
import com.capstonebangkit.plantdoc.SessionManager
import com.capstonebangkit.plantdoc.ui.settings.SettingsPreference
import com.capstonebangkit.plantdoc.ui.settings.SettingsViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var session: SessionManager
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var pref: SettingsPreference
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.tvToRegister.setOnClickListener { toRegister() }
        binding.buttonLogin.setOnClickListener { checkLogin() }

        session = SessionManager(this)
        pref = SettingsPreference.getInstance(dataStore)
        val themeViewModel = ViewModelProvider(this,
            com.capstonebangkit.plantdoc.ui.settings.ViewModelFactory(pref)
        ).get(SettingsViewModel::class.java)
        themeViewModel.getThemeSettings().observe(this) { isDarkModeActivate ->
            if (isDarkModeActivate){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        setupViewModel()
        playAnimation()
    }

    private fun playAnimation() {
        val welcome = ObjectAnimator.ofFloat(binding.tvWelcome, View.ALPHA, 1f).setDuration(500)
        val desc = ObjectAnimator.ofFloat(binding.tvDesc, View.ALPHA, 1f).setDuration(500)
        val username = ObjectAnimator.ofFloat(binding.edLoginUsername, View.ALPHA, 1f).setDuration(500)
        val pw = ObjectAnimator.ofFloat(binding.edLoginPassword, View.ALPHA, 1f).setDuration(500)
        val button = ObjectAnimator.ofFloat(binding.buttonLogin, View.ALPHA, 1f).setDuration(500)
        val register = ObjectAnimator.ofFloat(binding.tvToRegister, View.ALPHA, 1f).setDuration(500)
        val together = AnimatorSet().apply {
            playTogether(username, pw)
        }

        AnimatorSet().apply {
            playSequentially(welcome, desc, together, button, register)
            start()
        }
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
    }

    private fun toRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
        finish()
    }

    private fun checkLogin() {
        val username = binding.edLoginUsername.text.toString()
        val password = binding.edLoginPassword.text.toString()

        if (username.isEmpty()) {
            binding.edLoginUsername.requestFocus()
        } else if (password.isEmpty()) {
            binding.edLoginPassword.requestFocus()
        } else {
            sendToAPI(username, password)
        }
    }

    private fun sendToAPI(username: String, password: String) {
        viewModel.vmLogin(username, password).observe(this@LoginActivity) {

            when (it) {
                is Result.Loading -> {
                    showLoading(true)
                }

                is Result.Success -> {
                    showLoading(false)
                    val responseBody = it.data.loginResult
                    session.apply {
                        setBooleanPref(KEY_IS_LOGIN, true)
                    }
                    val i = Intent(this@LoginActivity, MainActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(i)
                    finish()
                }

                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        val isLogin = session.isLogin
        if (isLogin) {
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            finish()
        }
    }
}