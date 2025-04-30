package com.cambly.bibleverseviewer.di

import com.cambly.bibleverseviewer.data.remote.BibleApiService
import com.cambly.bibleverseviewer.data.repository.BibleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBibleApi(): BibleApiService = Retrofit.Builder()
        .baseUrl("https://bible-api.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BibleApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: BibleApiService): BibleRepository = BibleRepository(api)
}