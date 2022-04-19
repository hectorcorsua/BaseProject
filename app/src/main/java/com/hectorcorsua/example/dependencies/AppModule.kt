package com.hectorcorsua.example.dependencies

import android.app.Application
import androidx.room.Room
import com.hectorcorsua.example.data.api.ApiClient
import com.hectorcorsua.example.data.api.RetrofitBuilder
import com.hectorcorsua.example.data.database.ExampleDao
import com.hectorcorsua.example.data.database.ExampleDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApiServiceClient():ApiClient =
        RetrofitBuilder.build()
            .create(ApiClient::class.java)

    @Singleton
    @Provides
    fun provideRoom(application: Application): ExampleDao =
        Room
            .databaseBuilder(application, ExampleDb::class.java, "examples")
            .build().exampleDao()

}