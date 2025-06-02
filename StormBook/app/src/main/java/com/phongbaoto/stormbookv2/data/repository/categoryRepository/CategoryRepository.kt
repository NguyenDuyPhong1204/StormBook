package com.phongbaoto.stormbookv2.data.repository.categoryRepository

import com.phongbaoto.stormbookv2.data.model.Category
import com.phongbaoto.stormbookv2.data.remote.CategoryApiService
import com.phongbaoto.stormbookv2.utils.apiCall
import jakarta.inject.Inject

class CategoryRepository @Inject constructor(
    private val apiService: CategoryApiService
) {
    suspend fun getListCategory(): Result<List<Category>> {
        return apiCall {
            apiService.getListCategory().data
        }
    }

    suspend fun getCategoryByStoryId(id: Long): Result<Category> {
        return apiCall {
            apiService.getCategoryByStoryId(id).data
        }
    }

}