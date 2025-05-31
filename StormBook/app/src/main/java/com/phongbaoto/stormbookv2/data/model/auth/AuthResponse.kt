package com.phongbaoto.stormbookv2.data.model.auth

data class AuthResponse(
    val id: Long,
    val email: String,
    val fullName: String,
    val role: Role,
    val avatar: String,
    val verified: Boolean,
    val status: Status,
    val createdAt: String,
    val updatedAt: String
)
enum class Role {
    USER, STORY_USER, ADMIN
}

enum class Status {
    ACTIVE, BAN
}