package com.layanan.jurusan.ui.announcement

import android.content.Intent
import android.os.Build
import android.text.Html
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
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.databinding.ItemAnnouncementBinding
import com.layanan.jurusan.ui.news.ListNewsAdapter

class ListAnnouncementAdapter : PagingDataAdapter<AnnouncementModel, ListAnnouncementAdapter.ListAnnouncementViewHolder>(DiffCallback) {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<AnnouncementModel>() {
            override fun areItemsTheSame(oldItem: AnnouncementModel, newItem: AnnouncementModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AnnouncementModel, newItem: AnnouncementModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: AnnouncementModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: ListAnnouncementAdapter.ListAnnouncementViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAnnouncementViewHolder {
        val binding = ItemAnnouncementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListAnnouncementViewHolder(binding)
    }


    inner class ListAnnouncementViewHolder(val binding: ItemAnnouncementBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: AnnouncementModel){
            with(binding){
                titleAnnouncement.text = data?.title
                Glide.with(itemView.context)
                        .load(data?.image)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error))
                        .into(imgAnnouncement)
                snippetAnnouncement.text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    Html.fromHtml(data.snippet, Html.FROM_HTML_MODE_COMPACT)
                }else{
                    Html.fromHtml(data.snippet)
                }
                itemView.setOnClickListener {
                    val intent =  Intent(itemView.context, AnnouncementActivity::class.java)
//                    intent.putExtra(AnnouncementActivity.EXTRA_ANNOUNCEMENT, data?.id)
//                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("CountItem",super.getItemCount().toString())
        return super.getItemCount()
    }
}