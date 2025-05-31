package com.phongbaoto.stormbookv2.data.repository.authRepository

import com.phongbaoto.stormbookv2.data.model.ApiResponse
import com.phongbaoto.stormbookv2.data.model.auth.AuthResponse
import com.phongbaoto.stormbookv2.data.model.auth.LoginGoogleRequest
import com.phongbaoto.stormbookv2.data.model.auth.LoginRequest
import com.phongbaoto.stormbookv2.data.model.auth.RegisterRequest
import com.phongbaoto.stormbookv2.data.remote.AuthApiService
import jakarta.inject.Inject
import com.phongbaoto.stormbookv2.utils.apiCall

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