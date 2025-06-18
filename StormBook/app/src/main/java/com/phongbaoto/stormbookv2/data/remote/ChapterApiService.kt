package com.phongbaoto.stormbookv2.data.remote

import com.phongbaoto.stormbookv2.data.model.ApiResponse
import com.phongbaoto.stormbookv2.data.model.Chapter
import retrofit2.http.GET
import retrofit2.http.Path

interface ChapterApiService {
    @GET("/api/chapter/{chapterId}")
    suspend fun getChapterById(@Path("chapterId") chapterId: Long): ApiResponse<Chapter>

    @GET("/api/chapter/list-chapter-read/{userId}/{storyId}")
    suspend fun getListChapterRead(
        @Path("userId") userId: Long,
        @Path("storyId") storyId: Long
    ): ApiResponse<List<Chapter>>

}