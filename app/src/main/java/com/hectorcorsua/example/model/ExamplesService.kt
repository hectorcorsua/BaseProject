package com.hectorcorsua.example.model

import com.hectorcorsua.example.data.api.ApiClient
import com.hectorcorsua.example.data.api.ExampleRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExamplesService @Inject constructor(
    private val api : ApiClient
) {
    suspend fun get() : List<ExampleRemote> {
        return withContext(Dispatchers.IO) {
            val response = api.getExamples()
            response.body() ?: emptyList()
        }
    }
}