package com.hectorcorsua.example.model

import com.hectorcorsua.example.data.database.ExampleDao
import com.hectorcorsua.example.data.database.ExampleEntity
import javax.inject.Inject

class FavoritesDbRepository @Inject constructor(
    private val database: ExampleDao
) {

    suspend fun get(): List<Example> = database.get().toExamples()

    suspend fun save(example: Example) {
        database.insert(example.toEntity())
    }

    suspend fun exist(example: Example): Boolean =
        database.getById(example.id) != null

    suspend fun remove(example: Example) {
        database.removeById(example.id)
    }

    private fun Example.toEntity(): ExampleEntity =
        ExampleEntity(
            id = this.id,
            name = this.name,
            description = this.description,
            image = this.image
        )

    private fun ExampleEntity.toExample(): Example =
        Example(
            id = this.id,
            name = this.name,
            description = this.description,
            image = this.image
        )

    private fun List<ExampleEntity>.toExamples(): List<Example> =
        this.map { it.toExample() }
}