package com.layanan.jurusan.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Announcement
import com.layanan.jurusan.databinding.ItemAnnouncementBinding

class HomeAnnouncementAdapter(private val list: List<Announcement>, val context: Context) : RecyclerView.Adapter<HomeAnnouncementAdapter.HomeAnnouncementViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Announcement)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAnnouncementViewHolder {
        val binding = ItemAnnouncementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeAnnouncementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAnnouncementViewHolder, position: Int) {
        val news = list[position]
        holder.bind(news)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size

    class HomeAnnouncementViewHolder(private val binding: ItemAnnouncementBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Announcement) {
            with(binding) {
//                tvItemKode.text = barang.kode
//                Glide.with(itemView.context)
//                    .load(barang.image)
//                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
//                    .into(imgItemImage)
            }
        }
    }
}