package com.layanan.jurusan.ui.home

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.databinding.ItemAnnouncementBinding

class HomeAnnouncementAdapter(private val list: List<AnnouncementModel>, val context: Context) : RecyclerView.Adapter<HomeAnnouncementAdapter.HomeAnnouncementViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: AnnouncementModel)
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
        fun bind(data: AnnouncementModel) {
            with(binding) {
                titleAnnouncement.text = data.title
                snippetAnnouncement.text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    Html.fromHtml(data.desc, Html.FROM_HTML_MODE_COMPACT)
                }else{
                    Html.fromHtml(data.desc)
                }
                Glide.with(itemView.context)
                    .load(data.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgAnnouncement)
            }
        }
    }
}