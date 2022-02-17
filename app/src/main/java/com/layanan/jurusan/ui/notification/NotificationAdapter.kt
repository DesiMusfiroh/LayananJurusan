package com.layanan.jurusan.ui.notification

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.NotificationModel
import com.layanan.jurusan.data.model.NotifikasiModel
import com.layanan.jurusan.databinding.ItemNotificationBinding

class NotificationAdapter(private val list: List<NotifikasiModel>) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: NotifikasiModel)
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
        fun bind(data: NotifikasiModel) {
            with(binding) {
                tvTitle.text = data.title
                tvDatetime.text = data.createdAd

                tvDescription.text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    Html.fromHtml(data.message, Html.FROM_HTML_MODE_COMPACT)
                }else{
                    Html.fromHtml(data.message)
                }
            }
        }
    }
}