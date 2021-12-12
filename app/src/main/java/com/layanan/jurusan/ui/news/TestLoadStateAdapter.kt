package com.layanan.jurusan.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.R
import com.layanan.jurusan.databinding.LayoutLoadStateBinding

class TestLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<TestLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LayoutLoadStateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return LoadStateViewHolder(binding)
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_load_state,parent,false)
        )
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
        val btnLoadStateRetry = holder.itemView.findViewById<Button>(R.id.btnLoadStateRetry)
        btnLoadStateRetry.setOnClickListener {
            retry.invoke()
        }
    }

    inner class LoadStateViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val btnLoadStateRetry = itemView.findViewById<Button>(R.id.btnLoadStateRetry)
        val tvLoadStateErrorMessage = itemView.findViewById<TextView>(R.id.tvLoadStateErrorMessage)
        fun bind(loadState: LoadState) {
            btnLoadStateRetry.isVisible = loadState !is LoadState.Loading
            tvLoadStateErrorMessage.isVisible = loadState !is LoadState.Loading
        }
    }
}