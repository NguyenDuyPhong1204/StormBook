package com.phongbaoto.vnstormbook.data.model.auth

data class RegisterRequest(
    val email: String,
    val password: String,
    val fullName: String,
)