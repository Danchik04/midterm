package com.example.midterm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_exercises")
data class CompletedExercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val day: String,
    val exercise: String,
    val isChecked: Boolean
) 