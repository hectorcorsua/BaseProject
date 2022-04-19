package com.hectorcorsua.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExampleDao {

    @Query("SELECT * FROM EXAMPLES")
    suspend fun get(): List<ExampleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(example: ExampleEntity)

    @Query("SELECT * FROM EXAMPLES WHERE id = :exampleId")
    suspend fun getById(exampleId: Int): ExampleEntity?

    @Query("DELETE FROM EXAMPLES WHERE id = :exampleId")
    suspend fun removeById(exampleId: Int)

}