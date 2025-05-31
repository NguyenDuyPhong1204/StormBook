package com.phongbaoto.stormbookv2.data.model

data class Comment(
    val avatar: String,
    val nameUser: String,
    val comment: String,
    val countLike: Int,
    val timeComment: String
)