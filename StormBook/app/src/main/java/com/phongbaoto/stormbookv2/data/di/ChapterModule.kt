package com.phongbaoto.stormbookv2.data.di

import com.phongbaoto.stormbookv2.data.remote.ChapterApiService
import com.phongbaoto.stormbookv2.data.repository.chapterRepository.ChapterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ChapterModule {
    @Provides
    fun provideChapterApiService(retrofit: Retrofit): ChapterApiService {
        return retrofit.create(ChapterApiService::class.java)
    }

    @Provides
    fun provideChapterRepository(api: ChapterApiService): ChapterRepository {
        return ChapterRepository(api)
    }
}