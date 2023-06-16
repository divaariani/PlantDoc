package com.capstonebangkit.plantdoc.user

import androidx.lifecycle.*
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    fun vmRegister(name: String, email: String, password: String) =
        repository.repoRegister(name, email, password)

    fun vmLogin(username: String, password: String) =
        repository.repoLogin(username, password)
}