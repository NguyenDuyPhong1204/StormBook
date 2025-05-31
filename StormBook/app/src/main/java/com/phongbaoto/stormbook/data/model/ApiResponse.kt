package com.phongbaoto.stormbook.data.model

data class ApiResponse<T>(
    val status: Int,
    val message: String,
    val data: T
)