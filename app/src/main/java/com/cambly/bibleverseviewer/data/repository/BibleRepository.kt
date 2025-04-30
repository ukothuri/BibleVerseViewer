package com.cambly.bibleverseviewer.data.repository

import com.cambly.bibleverseviewer.data.model.Verse
import com.cambly.bibleverseviewer.data.remote.BibleApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BibleRepository @Inject constructor(private val api: BibleApiService) {
    fun fetchVerses(): Flow<List<Verse>> = flow {
        val response = api.getVerses()
        if (response.isSuccessful) {
            emit(response.body()?.verses ?: emptyList())
        } else {
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)
}