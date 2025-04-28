package com.example.midterm.presentation.mvi
 
sealed class ExampleIntent {
    object LoadExamples : ExampleIntent()
    data class UpdateExample(val id: String, val newValue: String) : ExampleIntent()
    object ClearExamples : ExampleIntent()
} 