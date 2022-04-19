package com.hectorcorsua.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ExampleEntity::class],
    version = 1
)
abstract class ExampleDb : RoomDatabase(){
    abstract fun exampleDao(): ExampleDao
}