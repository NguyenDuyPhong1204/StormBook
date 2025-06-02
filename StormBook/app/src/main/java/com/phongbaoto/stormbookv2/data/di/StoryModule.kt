package com.phongbaoto.stormbookv2.data.di

import com.phongbaoto.stormbookv2.data.remote.StoryApiService
import com.phongbaoto.stormbookv2.data.repository.storyRepository.StoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object StoryModule {
    @Provides
    fun provideStoryApiService(retrofit: Retrofit): StoryApiService {
        return retrofit.create(StoryApiService::class.java)
    }

    @Provides
    fun provideStoryRepository(api: StoryApiService): StoryRepository {
        return StoryRepository(api)
    }


}