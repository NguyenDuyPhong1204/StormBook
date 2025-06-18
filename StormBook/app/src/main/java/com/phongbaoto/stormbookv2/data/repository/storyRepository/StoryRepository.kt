package com.phongbaoto.stormbookv2.data.repository.storyRepository

import com.phongbaoto.stormbookv2.data.model.ApiResponse
import com.phongbaoto.stormbookv2.data.model.Chapter
import com.phongbaoto.stormbookv2.data.model.Story
import com.phongbaoto.stormbookv2.data.remote.StoryApiService
import com.phongbaoto.stormbookv2.utils.apiCall
import jakarta.inject.Inject

class StoryRepository @Inject constructor(
    private val apiService: StoryApiService
) {
    suspend fun getSuggestedStories(): Result<List<Story>> {
        return apiCall {
            apiService.getSuggestedStories().data
        }
    }

    suspend fun getHotWeekStories(): Result<List<Story>> {
        return apiCall {
            apiService.getHotWeekStories().data
        }
    }

    suspend fun getAllStory(
        page: Int,
        size: Int
    ): Result<List<Story>> {
        return apiCall {
            apiService.getAllStory(
                page = page,
                limit = size
            ).data
        }
    }

    suspend fun getStoryById(storyId: Long): Result<Story> {
        return apiCall {
            apiService.getStoryById(storyId).data
        }
    }

    suspend fun getChapterByStoryId(storyId: Long): Result<List<Chapter>> {
        return apiCall {
            apiService.getChapterByStoryId(storyId).data
        }
    }

    suspend fun getStoryByCategoryId(
        categoryId: Long,
        page: Int,
        size: Int
    ): Result<List<Story>> {
        return apiCall {
            apiService.getStoryByCategoryId(
                categoryId,
                page,
                size
            ).data
        }
    }

}