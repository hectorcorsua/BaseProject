package com.hectorcorsua.example.data.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("path")
    suspend fun getExamples(): Response<List<ExampleRemote>>
}