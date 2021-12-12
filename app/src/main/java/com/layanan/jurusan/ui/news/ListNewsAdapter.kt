package com.layanan.jurusan.ui.news

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.databinding.ItemNewsBinding

class ListNewsAdapter : PagingDataAdapter<NewsModel, ListNewsAdapter.ListNewsViewHolder>(DiffCallback) {

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<NewsModel>() {
            override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: NewsModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: ListNewsViewHolder, position: Int) {
        val news = getItem(position)
        if (news != null) {
            holder.bind(news)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListNewsViewHolder(binding)
    }


    inner class ListNewsViewHolder(
        val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: NewsModel){
            with(binding){
                tvItemTitle.text = data?.title
                Glide.with(itemView.context)
                    .load(data?.image)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgItemImage)

                itemView.setOnClickListener {
                    val intent =  Intent(itemView.context, NewsActivity::class.java)
                    intent.putExtra(NewsActivity.EXTRA_NEWS, data?.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("CountItem",super.getItemCount().toString())
        return super.getItemCount()
    }
}