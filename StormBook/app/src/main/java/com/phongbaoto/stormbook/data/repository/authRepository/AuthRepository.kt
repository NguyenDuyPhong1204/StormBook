package com.phongbaoto.stormbook.data.repository.authRepository

import com.phongbaoto.stormbook.data.model.ApiResponse
import com.phongbaoto.stormbook.data.model.auth.AuthResponse
import com.phongbaoto.stormbook.data.model.auth.LoginGoogleRequest
import com.phongbaoto.stormbook.data.model.auth.LoginRequest
import com.phongbaoto.stormbook.data.model.auth.RegisterRequest
import com.phongbaoto.stormbook.data.remote.AuthApiService
import jakarta.inject.Inject
import org.json.JSONException
import org.json.JSONObject
import com.phongbaoto.stormbook.utils.apiCall

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