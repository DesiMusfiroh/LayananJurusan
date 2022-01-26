package com.layanan.jurusan.ui.iku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Iku5Model
import com.layanan.jurusan.databinding.ItemIku5Binding

class Iku5Adapter(private val list: List<Iku5Model>) : RecyclerView.Adapter<Iku5Adapter.Iku5ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Iku5Model)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    class Iku5ViewHolder(private val binding: ItemIku5Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Iku5Model){
            with(binding){
                tvNama.text = data.nama
                tvNoInduk.text = data.noInduk
                tvJenisKegiatan.text = data.hasilKerjaDosen?.jenisKegiatan
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Iku5ViewHolder {
        val binding = ItemIku5Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Iku5ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Iku5ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount() = list.size

}