package com.example.midterm.domain.repository

import com.example.midterm.domain.model.Example

interface ExampleRepository {
    suspend fun getExamples(): List<Example>
    suspend fun getExampleById(id: String): Example?
    suspend fun saveExample(example: Example)
    suspend fun deleteExample(id: String)
} 