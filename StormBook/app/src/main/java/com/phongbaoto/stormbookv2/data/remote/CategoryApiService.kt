package com.phongbaoto.stormbookv2.data.remote

import com.phongbaoto.stormbookv2.data.model.ApiResponse
import com.phongbaoto.stormbookv2.data.model.Category
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApiService {
    @GET("/api/category/list-category")
    suspend fun getListCategory(): ApiResponse<List<Category>>
    //lay danh sach the loai theo id truyen
    @GET("/api/category-by-storeId/{id}")
    suspend fun getCategoryByStoryId(@Path("id") id: Long): ApiResponse<Category>
}