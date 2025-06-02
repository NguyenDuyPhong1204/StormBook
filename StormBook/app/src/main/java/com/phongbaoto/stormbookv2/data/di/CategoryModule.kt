package com.phongbaoto.stormbookv2.data.di

import com.phongbaoto.stormbookv2.data.remote.CategoryApiService
import com.phongbaoto.stormbookv2.data.repository.categoryRepository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {
    @Provides
    fun provideCategoryApiService(retrofit: Retrofit): CategoryApiService {
        return retrofit.create(CategoryApiService::class.java)
    }

    @Provides
    fun provideCategoryRepository(api: CategoryApiService): CategoryRepository {
        return CategoryRepository(api)
    }
}