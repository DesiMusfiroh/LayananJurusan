package com.layanan.jurusan.ui.news

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.databinding.ItemTestBinding

class TestNewsAdapter: PagingDataAdapter<NewsModel, TestNewsAdapter.TestNewsViewHolder>(DiffCallback) {

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

//    class TestNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(data: NewsModel?) {
//            val testTitleNews = itemView.findViewById<TextView>(R.id.test_title_news)
//            testTitleNews.text = data!!.title
//        }
//    }

    inner class TestNewsViewHolder(
        val binding: ItemTestBinding
    ) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: TestNewsAdapter.TestNewsViewHolder, position: Int) {
        val news = getItem(position)

        holder.binding.apply {

            holder.itemView.apply {
                testTitleNews.text = news?.title ?: "Test"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestNewsAdapter.TestNewsViewHolder {
        return TestNewsViewHolder(
            ItemTestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        Log.d("CountItem",super.getItemCount().toString())
        return super.getItemCount()
    }



}