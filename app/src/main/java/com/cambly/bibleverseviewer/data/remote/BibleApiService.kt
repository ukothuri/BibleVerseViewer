package com.cambly.bibleverseviewer.data.remote



import retrofit2.Response
import retrofit2.http.GET


interface BibleApiService {
    @GET("matt%2025:31-33,46")
    suspend fun getVerses(): Response<BibleResponse>
}