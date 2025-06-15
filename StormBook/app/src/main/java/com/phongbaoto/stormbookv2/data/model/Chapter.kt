package com.phongbaoto.stormbookv2.data.model

import java.time.Instant
data class Chapter(
    val id: Long,
    val chapterNumber: Int,
    val title: String?,
    val storyId: Long,
    val view_count: Int,
    val is_read: Boolean,
    val image_url: List<String>,
    val createdAt: String,
    val updatedAt: String
)
