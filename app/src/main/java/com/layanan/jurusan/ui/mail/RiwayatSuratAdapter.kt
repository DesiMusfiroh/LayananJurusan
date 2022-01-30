package com.layanan.jurusan.ui.mail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.RiwayatSuratModel
import com.layanan.jurusan.databinding.ItemRiwayatSuratBinding

class RiwayatSuratAdapter(private val list: List<RiwayatSuratModel>, val context: Context) : RecyclerView.Adapter<RiwayatSuratAdapter.RiwayatSuratViewHolder>()  {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: RiwayatSuratModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatSuratViewHolder {
        val binding = ItemRiwayatSuratBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RiwayatSuratViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RiwayatSuratViewHolder, position: Int) {
        val riwayatSurat = list[position]
        holder.bind(riwayatSurat)
        holder.itemView.setOnClickListener {
            @Suppress("DEPRECATION")
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size

    class RiwayatSuratViewHolder(private val binding: ItemRiwayatSuratBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RiwayatSuratModel) {
            with(binding) {
                tvTitle.text = data.jenisSurat?.judul
                tvStatus.text = data.status
                tvDatetime.text = data.tanggalPengajuan
            }
        }
    }
}