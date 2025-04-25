package com.itskidan.data.di

import com.itskidan.data.config.BuildConfigProvider
import com.itskidan.data.retrofit.api.PushApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(buildConfigProvider: BuildConfigProvider): Retrofit {
        return Retrofit.Builder()
            .baseUrl(buildConfigProvider.getNotifyFcmBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePushApi(retrofit: Retrofit): PushApi {
        return retrofit.create(PushApi::class.java)
    }


}