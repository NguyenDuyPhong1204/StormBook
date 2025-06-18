package com.phongbaoto.stormbookv2.data.model

data class Story(
    val id: Long,
    val author: String,
    val title: String,
    val trans_group: String,
    val cover_image: String,
    val created_at: String,
    val rating: Double,
    val status: STATUS,
    val updated_at: String,
    val view_count: Int,
    val like_count: Int,
    val description: String,
    val total_chapters: Int,
    val categoryId: List<Int>
)

enum class STATUS{
    Completed,
    Dropped,
    OnGoing
}