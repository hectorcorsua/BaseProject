package com.hectorcorsua.example.model

import com.hectorcorsua.example.data.api.ExampleRemote
import javax.inject.Inject

class ExampleRepository @Inject constructor(
    private val api : ExamplesService
) {

    suspend fun get(): List<Example>  = api.get().toExamples()

    private fun ExampleRemote.toExample(): Example =
        Example(
            id = this.id,
            name = this.name,
            description = this.description,
            image = this.image
        )

    private fun List<ExampleRemote>.toExamples(): List<Example> =
        this.map { it.toExample() }
}