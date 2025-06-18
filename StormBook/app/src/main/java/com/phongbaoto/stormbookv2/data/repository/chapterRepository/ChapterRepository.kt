package com.phongbaoto.stormbookv2.data.repository.chapterRepository

import com.phongbaoto.stormbookv2.data.model.Chapter
import com.phongbaoto.stormbookv2.data.remote.ChapterApiService
import com.phongbaoto.stormbookv2.utils.apiCall
import jakarta.inject.Inject

class ChapterRepository @Inject constructor(
    private val apiService: ChapterApiService
){
    suspend fun getChapterById(chapterId: Long): Result<Chapter>{
        return apiCall {
            apiService.getChapterById(chapterId).data
        }
    }

    suspend fun getListReadChapter(userId: Long, storyId: Long): Result<List<Chapter>>{
        return apiCall {
            apiService.getListChapterRead(userId, storyId).data
        }
    }
}