package com.layanan.jurusan.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.databinding.LoadFooterBinding

class NewsLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<NewsLoadStateAdapter.LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LoadFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val binding: LoadFooterBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }
        }
        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                tvError.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}