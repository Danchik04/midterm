package com.example.midterm.data.mapper

import com.example.midterm.data.local.entity.ExampleEntity
import com.example.midterm.domain.model.Example
import javax.inject.Inject

class ExampleMapper @Inject constructor() {
    fun toDomain(entity: ExampleEntity): Example {
        return Example(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            additionalInfo = entity.newColumn
        )
    }

    fun toEntity(domain: Example): ExampleEntity {
        return ExampleEntity(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            newColumn = domain.additionalInfo
        )
    }
} 