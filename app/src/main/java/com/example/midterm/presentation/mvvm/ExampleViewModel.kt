package com.example.midterm.presentation.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midterm.domain.model.Example
import com.example.midterm.domain.usecase.GetExamplesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val getExamplesUseCase: GetExamplesUseCase
) : ViewModel() {

    private val _examples = MutableLiveData<List<Example>>()
    val examples: LiveData<List<Example>> = _examples

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadExamples() {
        viewModelScope.launch {
            try {
                val result = getExamplesUseCase.execute()
                _examples.value = result
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
} 