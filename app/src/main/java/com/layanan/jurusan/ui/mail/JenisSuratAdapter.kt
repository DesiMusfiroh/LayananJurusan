package com.layanan.jurusan.ui.mail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.JenisSuratModel
import com.layanan.jurusan.databinding.ItemJenisSuratBinding

class JenisSuratAdapter(private val list: List<JenisSuratModel>, val context: Context) : RecyclerView.Adapter<JenisSuratAdapter.JenisSuratViewHolder>()  {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: JenisSuratModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JenisSuratViewHolder {
        val binding = ItemJenisSuratBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JenisSuratViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JenisSuratViewHolder, position: Int) {
        val jenisSurat = list[position]
        holder.bind(jenisSurat)
        holder.itemView.setOnClickListener {
            @Suppress("DEPRECATION")
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size

    class JenisSuratViewHolder(private val binding: ItemJenisSuratBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: JenisSuratModel) {
            with(binding) {
                tvJudul.text = data.judul
                tvTipe.text = data.tipe
            }
        }
    }
}