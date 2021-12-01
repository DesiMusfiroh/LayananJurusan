package com.layanan.jurusan.ui.news

import android.content.Intent
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

class ListNewsAdapter : PagingDataAdapter<NewsModel, ListNewsAdapter.ListNewsViewHolder>(DIFF_CALLBACK_NEWS) {

    companion object {
        private val DIFF_CALLBACK_NEWS = object : DiffUtil.ItemCallback<NewsModel>() {
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
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(currentItem!!)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListNewsViewHolder(binding)
    }

    class ListNewsViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NewsModel?) {
            with(binding) {
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

}