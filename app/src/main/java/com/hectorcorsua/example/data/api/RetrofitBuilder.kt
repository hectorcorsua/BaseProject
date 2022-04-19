package com.hectorcorsua.example.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    fun build(): Retrofit =
        Retrofit.Builder()
            .baseUrl("url base")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}