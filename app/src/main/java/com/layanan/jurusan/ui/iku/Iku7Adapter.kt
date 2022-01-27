package com.layanan.jurusan.ui.iku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Iku6Model
import com.layanan.jurusan.data.model.Iku7Model
import com.layanan.jurusan.databinding.ItemIku6Binding
import com.layanan.jurusan.databinding.ItemIku7Binding

class Iku7Adapter(private val list: List<Iku7Model>): RecyclerView.Adapter<Iku7Adapter.Iku7ViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Iku7Model)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    class Iku7ViewHolder(private val binding: ItemIku7Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Iku7Model){
            with(binding){
                tvMataKuliah.text = data.mataKuliah
                tvMetodePembelajaran.text = data.metodePembelajaran
                tvNoSk.text = data.noSk
                tvProdi.text = data.namaProdi
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Iku7ViewHolder {
        val binding = ItemIku7Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Iku7ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Iku7ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount() = list.size

}