package com.capstonebangkit.plantdoc.user

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.capstonebangkit.plantdoc.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.tvToLogin.setOnClickListener { toLogin() }
        binding.buttonRegister.setOnClickListener { checkRegister() }

        setupViewModel()
        playAnimation()
    }

    private fun playAnimation() {
        val welcome = ObjectAnimator.ofFloat(binding.tvWelcome, View.ALPHA, 1f).setDuration(500)
        val desc = ObjectAnimator.ofFloat(binding.tvDesc, View.ALPHA, 1f).setDuration(500)
        val username = ObjectAnimator.ofFloat(binding.edRegisterName, View.ALPHA, 1f).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.edRegisterEmail, View.ALPHA, 1f).setDuration(500)
        val pw = ObjectAnimator.ofFloat(binding.edRegisterPassword, View.ALPHA, 1f).setDuration(500)
        val button = ObjectAnimator.ofFloat(binding.buttonRegister, View.ALPHA, 1f).setDuration(500)
        val login = ObjectAnimator.ofFloat(binding.tvToLogin, View.ALPHA, 1f).setDuration(500)
        val together = AnimatorSet().apply {
            playTogether(username, email, pw)
        }

        AnimatorSet().apply {
            playSequentially(welcome, desc, together, button, login)
            start()
        }
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
    }

    private fun toLogin() {
        val i = Intent(this, LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
        finish()
    }

    private fun checkRegister() {
        val username = binding.edRegisterName.text.toString()
        val email = binding.edRegisterEmail.text.toString()
        val password = binding.edRegisterPassword.text.toString()

        if (username.isEmpty()) {
            binding.edRegisterName.requestFocus()
        } else if (email.isEmpty()) {
            binding.edRegisterEmail.requestFocus()
        } else if (password.isEmpty()) {
            binding.edRegisterPassword.requestFocus()
        } else {
            sendToAPI(username, email, password)
        }
    }

    private fun sendToAPI(username: String, email: String, password: String) {

        viewModel.vmRegister(username, email, password).observe(this) {

            when (it) {
                is Result.Loading -> {
                    showLoading(true)
                }

                is Result.Success -> {
                    showLoading(false)
                    Toast.makeText(this, it.data.message, Toast.LENGTH_SHORT).show()
                    toLogin()
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
}