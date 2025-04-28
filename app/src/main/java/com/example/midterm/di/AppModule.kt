package com.example.midterm.di

import android.content.Context
import androidx.room.Room
import com.example.midterm.data.local.AppDatabase
import com.example.midterm.data.local.dao.ExampleDao
import com.example.midterm.data.local.dao.CompletedExerciseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExampleDao(database: AppDatabase): ExampleDao {
        return database.exampleDao()
    }

    @Provides
    @Singleton
    fun provideCompletedExerciseDao(database: AppDatabase): CompletedExerciseDao {
        return database.completedExerciseDao()
    }
} 