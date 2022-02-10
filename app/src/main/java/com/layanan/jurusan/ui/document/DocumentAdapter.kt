package com.layanan.jurusan.ui.document

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.DocumentModel
import com.layanan.jurusan.databinding.ItemDocumentBinding

class DocumentAdapter(private val list: List<DocumentModel>) : RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: DocumentModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class DocumentViewHolder(private val binding: ItemDocumentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DocumentModel){
            with(binding){
                tvCategory.text = data.category
                titleDocument.text = data.title
                titleDate.text = data.published_at
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val binding = ItemDocumentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DocumentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size
}