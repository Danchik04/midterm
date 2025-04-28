package com.example.midterm.data.local.dao

import androidx.room.*
import com.example.midterm.data.local.entity.CompletedExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface CompletedExerciseDao {
    @Query("SELECT * FROM completed_exercises WHERE day = :day")
    fun getForDay(day: String): Flow<List<CompletedExercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(exercise: CompletedExercise)
} 