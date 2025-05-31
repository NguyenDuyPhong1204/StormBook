package com.phongbaoto.stormbookv2.data.model.auth

data class RegisterRequest(
    val email: String,
    val password: String,
    val fullName: String,
)