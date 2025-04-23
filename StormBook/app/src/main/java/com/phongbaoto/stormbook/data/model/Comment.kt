package com.phongbaoto.stormbook.data.model

data class Comment(
    val avatar: String,
    val nameUser: String,
    val comment: String,
    val countLike: Int,
    val timeComment: String
)