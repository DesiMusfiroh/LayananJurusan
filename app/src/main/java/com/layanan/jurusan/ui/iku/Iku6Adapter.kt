package com.layanan.jurusan.ui.iku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Iku6Model
import com.layanan.jurusan.databinding.ItemIku6Binding

class Iku6Adapter(private val list: List<Iku6Model>): RecyclerView.Adapter<Iku6Adapter.Iku6ViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Iku6Model)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class Iku6ViewHolder(private val binding: ItemIku6Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Iku6Model){
            with(binding){
                tvNamaMitra.text = data.mitra?.nama
                tvJenisKegiatan.text = data.jenisKegiatan
                tvProdi.text = data.namaProdi
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Iku6ViewHolder {
        val binding = ItemIku6Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Iku6ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Iku6ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount() = list.size

}