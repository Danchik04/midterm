package com.example.midterm.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm.databinding.ItemExampleBinding
import com.example.midterm.domain.model.Example

class ExampleAdapter : ListAdapter<Example, ExampleAdapter.ExampleViewHolder>(ExampleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val binding = ItemExampleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExampleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ExampleViewHolder(
        private val binding: ItemExampleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(example: Example) {
            binding.titleTextView.text = example.title
            binding.descriptionTextView.text = example.description
            example.additionalInfo?.let {
                binding.additionalInfoTextView.text = it
                binding.additionalInfoTextView.visibility = ViewGroup.VISIBLE
            } ?: run {
                binding.additionalInfoTextView.visibility = ViewGroup.GONE
            }
        }
    }

    private class ExampleDiffCallback : DiffUtil.ItemCallback<Example>() {
        override fun areItemsTheSame(oldItem: Example, newItem: Example): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Example, newItem: Example): Boolean {
            return oldItem == newItem
        }
    }
} 