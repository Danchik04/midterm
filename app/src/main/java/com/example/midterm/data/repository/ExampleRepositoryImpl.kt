package com.example.midterm.data.repository

import com.example.midterm.data.local.dao.ExampleDao
import com.example.midterm.data.mapper.ExampleMapper
import com.example.midterm.domain.model.Example
import com.example.midterm.domain.repository.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val dao: ExampleDao,
    private val mapper: ExampleMapper
) : ExampleRepository {

    override suspend fun getExamples(): List<Example> {
        return dao.getAll().map { mapper.toDomain(it) }
    }

    override suspend fun getExampleById(id: String): Example? {
        return dao.getById(id)?.let { mapper.toDomain(it) }
    }

    override suspend fun saveExample(example: Example) {
        dao.insert(mapper.toEntity(example))
    }

    override suspend fun deleteExample(id: String) {
        dao.deleteById(id)
    }
} 