package com.phongbaoto.stormbookv2.data.remote

import com.phongbaoto.stormbookv2.data.model.ApiResponse
import com.phongbaoto.stormbookv2.data.model.Chapter
import com.phongbaoto.stormbookv2.data.model.Story
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoryApiService {
    @GET("/api/story/suggested")
    suspend fun getSuggestedStories(): ApiResponse<List<Story>>

    @GET("/api/story/hot-week")
    suspend fun getHotWeekStories(): ApiResponse<List<Story>>

    @GET("/api/story/get-all-story")
    suspend fun getAllStory(
        @Query("page") page: Int = 1,
        @Query("size") limit: Int = 10
    ): ApiResponse<List<Story>>

    @GET("/api/story/{storyId}")
    suspend fun getStoryById(@Path("storyId") storyId: Long): ApiResponse<Story>

    @GET("/api/chapter/list-chapter/{storyId}")
    suspend fun getChapterByStoryId(@Path("storyId") storyId: Long): ApiResponse<List<Chapter>>
}