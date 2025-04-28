package com.example.midterm.domain.usecase

import com.example.midterm.domain.model.Example
import com.example.midterm.domain.repository.ExampleRepository
import javax.inject.Inject

class GetExamplesUseCase @Inject constructor(
    private val repository: ExampleRepository
) {
    suspend fun execute(): List<Example> {
        return repository.getExamples()
    }
} 