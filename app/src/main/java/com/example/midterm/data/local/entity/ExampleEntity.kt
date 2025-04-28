package com.example.midterm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "example_table")
data class ExampleEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val newColumn: String? = null
) 