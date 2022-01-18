package com.layanan.jurusan.ui.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.NotificationModel
import com.layanan.jurusan.databinding.ItemNotificationBinding

class NotificationAdapter(private val list: List<NotificationModel>, val context: Context) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: NotificationModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val news = list[position]
        holder.bind(news)
        holder.itemView.setOnClickListener {
            @Suppress("DEPRECATION")
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size

    class NotificationViewHolder(private val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NotificationModel) {
            with(binding) {
                tvTitle.text = data.title
                tvDescription.text = data.desc
                tvDatetime.text = data.datetime
            }
        }
    }
}