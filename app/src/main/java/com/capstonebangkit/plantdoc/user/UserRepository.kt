package com.capstonebangkit.plantdoc.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstonebangkit.plantdoc.SessionManager
import com.capstonebangkit.plantdoc.api.ApiService
import com.capstonebangkit.plantdoc.api.LoginResponse
import com.capstonebangkit.plantdoc.api.RegisterResponse

class UserRepository(private val sharedPref: SessionManager, private val apiService: ApiService) {
    fun repoRegister(
        name: String,
        email: String,
        password: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(name, email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
        Log.d("UserRepository", "Register")
    }

    fun repoLogin(username: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(username, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
        Log.d("UserRepository", "Login")
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            sharedPref: SessionManager,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(sharedPref, apiService)
            }.also { instance = it }
    }
}