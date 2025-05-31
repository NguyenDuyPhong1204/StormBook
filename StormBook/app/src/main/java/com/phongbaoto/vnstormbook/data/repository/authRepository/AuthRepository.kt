package com.phongbaoto.vnstormbook.data.repository.authRepository

import com.phongbaoto.vnstormbook.data.model.ApiResponse
import com.phongbaoto.vnstormbook.data.model.auth.AuthResponse
import com.phongbaoto.vnstormbook.data.model.auth.LoginGoogleRequest
import com.phongbaoto.vnstormbook.data.model.auth.LoginRequest
import com.phongbaoto.vnstormbook.data.model.auth.RegisterRequest
import com.phongbaoto.vnstormbook.data.remote.AuthApiService
import jakarta.inject.Inject
import com.phongbaoto.vnstormbook.utils.apiCall

class AuthRepository @Inject constructor(
    private val apiService: AuthApiService
) {
    suspend fun login(email: String, password: String): Result<AuthResponse> {
        return apiCall {
            apiService.login(LoginRequest(email, password)).data
        }
    }

    suspend fun loginWithGoogle(idToken: String): Result<AuthResponse> {
        return apiCall {
            apiService.loginGoogle(LoginGoogleRequest(idToken)).data
        }
    }

    suspend fun register(email: String, name: String, password: String): Result<ApiResponse<AuthResponse>> {
        return apiCall {
            apiService.register(RegisterRequest(email, name, password))
        }
    }



}