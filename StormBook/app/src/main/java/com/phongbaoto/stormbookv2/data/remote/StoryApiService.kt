package com.phongbaoto.stormbookv2.data.remote

import com.phongbaoto.stormbookv2.data.model.ApiResponse
import com.phongbaoto.stormbookv2.data.model.Story
import retrofit2.http.GET

interface StoryApiService {
    @GET("/api/story/suggested")
    suspend fun getSuggestedStories(): ApiResponse<List<Story>>
    @GET("/api/story/hot-week")
    suspend fun getHotWeekStories(): ApiResponse<List<Story>>
}