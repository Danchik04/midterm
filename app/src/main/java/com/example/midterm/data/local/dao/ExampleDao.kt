package com.example.midterm.data.local.dao

import androidx.room.*
import androidx.room.Dao
import com.example.midterm.data.local.entity.ExampleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExampleDao {
    @Query("SELECT * FROM example_table")
    fun getAll(): Flow<List<ExampleEntity>>

    @Query("SELECT * FROM example_table WHERE id = :id")
    suspend fun getById(id: String): ExampleEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(example: ExampleEntity)

    @Delete
    suspend fun delete(example: ExampleEntity)

    @Query("DELETE FROM example_table WHERE id = :id")
    suspend fun deleteById(id: String)
} 