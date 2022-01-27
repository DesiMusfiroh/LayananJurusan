package com.layanan.jurusan.ui.iku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Iku8Model
import com.layanan.jurusan.databinding.ItemIku8Binding

class Iku8Adapter(private val list: List<Iku8Model>): RecyclerView.Adapter<Iku8Adapter.Iku8ViewHolder>(){


    class Iku8ViewHolder(private val binding: ItemIku8Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Iku8Model){
            with(binding){
                tvProdi.text = data.namaProdi
                tvNamaLembaga.text = data.namaLembagaAkreditasi
                tvNoSk.text = data.noSk
                tvTahunSk.text = data.tahun
                tvTipeLembaga.text = data.tipeLembagaAkreditasi
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Iku8ViewHolder {
        val binding = ItemIku8Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Iku8ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Iku8ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount() = list.size

}