package com.example.midterm.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midterm.domain.usecase.GetExamplesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val getExamplesUseCase: GetExamplesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ExampleViewState())
    val state: StateFlow<ExampleViewState> = _state.asStateFlow()

    fun processIntent(intent: ExampleIntent) {
        when (intent) {
            is ExampleIntent.LoadExamples -> loadExamples()
            is ExampleIntent.UpdateExample -> updateExample(intent.id, intent.newValue)
            is ExampleIntent.ClearExamples -> clearExamples()
        }
    }

    private fun loadExamples() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val examples = getExamplesUseCase.execute()
                _state.value = _state.value.copy(
                    isLoading = false,
                    examples = examples,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    private fun updateExample(id: String, newValue: String) {
        // Implementation for updating an example
    }

    private fun clearExamples() {
        _state.value = _state.value.copy(examples = emptyList())
    }
} 