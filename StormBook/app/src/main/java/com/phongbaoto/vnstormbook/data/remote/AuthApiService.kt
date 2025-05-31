package com.phongbaoto.vnstormbook.data.remote

import com.phongbaoto.vnstormbook.data.model.ApiResponse
import com.phongbaoto.vnstormbook.data.model.auth.AuthResponse
import com.phongbaoto.vnstormbook.data.model.auth.LoginGoogleRequest
import com.phongbaoto.vnstormbook.data.model.auth.LoginRequest
import com.phongbaoto.vnstormbook.data.model.auth.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService{
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<AuthResponse>
    @POST("/api/auth/login-google")
    suspend fun loginGoogle(@Body request: LoginGoogleRequest): ApiResponse<AuthResponse>
    @POST("/api/auth/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse<AuthResponse>
}