package com.example.midterm.presentation.mvi

import com.example.midterm.domain.model.Example
 
data class ExampleViewState(
    val isLoading: Boolean = false,
    val examples: List<Example> = emptyList(),
    val error: String? = null
) 