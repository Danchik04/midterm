package com.example.midterm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.midterm.data.local.dao.ExampleDao
import com.example.midterm.data.local.entity.ExampleEntity
import com.example.midterm.data.local.dao.CompletedExerciseDao
import com.example.midterm.data.local.entity.CompletedExercise

@Database(
    entities = [ExampleEntity::class, CompletedExercise::class],
    version = 3,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
    abstract fun completedExerciseDao(): CompletedExerciseDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Example migration: Add a new column
                database.execSQL("ALTER TABLE example_table ADD COLUMN new_column TEXT")
            }
        }
    }
} 