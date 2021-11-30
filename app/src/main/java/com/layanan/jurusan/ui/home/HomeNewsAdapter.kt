package com.layanan.jurusan.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.databinding.ItemNewsHomeBinding

class HomeNewsAdapter(private val list: List<NewsModel>, val context: Context) : RecyclerView.Adapter<HomeNewsAdapter.HomeNewsViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: NewsModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsViewHolder {
        val binding = ItemNewsHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        val news = list[position]
        holder.bind(news)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size

    class HomeNewsViewHolder(private val binding: ItemNewsHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NewsModel) {
            with(binding) {
                tvItemTitle.text = data.title
                Glide.with(itemView.context)
                    .load(data.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgItemPhoto)
            }
        }
    }
}