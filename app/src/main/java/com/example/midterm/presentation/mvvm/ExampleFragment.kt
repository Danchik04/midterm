package com.example.midterm.presentation.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midterm.databinding.FragmentExampleBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExampleFragment : Fragment() {

    private var _binding: FragmentExampleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExampleViewModel by viewModels()
    private val adapter = ExampleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        viewModel.loadExamples()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ExampleFragment.adapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.examples.observe(viewLifecycleOwner) { examples ->
                    adapter.submitList(examples)
                }
                viewModel.error.observe(viewLifecycleOwner) { error ->
                    error?.let { showError(it) }
                }
            }
        }
    }

    private fun showError(message: String) {
        // Show error message to user
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 