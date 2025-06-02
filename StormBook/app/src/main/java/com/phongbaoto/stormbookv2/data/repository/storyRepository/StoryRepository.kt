package com.phongbaoto.stormbookv2.data.repository.storyRepository

import com.phongbaoto.stormbookv2.data.model.Story
import com.phongbaoto.stormbookv2.data.remote.StoryApiService
import com.phongbaoto.stormbookv2.utils.apiCall
import jakarta.inject.Inject

class StoryRepository @Inject constructor(
    private val apiService: StoryApiService
){
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

}