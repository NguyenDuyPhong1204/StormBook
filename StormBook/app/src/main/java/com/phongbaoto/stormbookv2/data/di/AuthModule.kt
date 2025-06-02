package com.phongbaoto.stormbookv2.data.di

import com.phongbaoto.stormbookv2.data.remote.AuthApiService
import com.phongbaoto.stormbookv2.data.repository.authRepository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    fun provideAuthRepository(api: AuthApiService): AuthRepository {
        return AuthRepository(api)
    }
}