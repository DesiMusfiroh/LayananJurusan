package com.layanan.jurusan.ui.iku

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Iku3Model
import com.layanan.jurusan.databinding.ItemIku3Binding

class Iku3Adapter(private val list: List<Iku3Model>) : RecyclerView.Adapter<Iku3Adapter.Iku3ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Iku3Model)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class Iku3ViewHolder(private val binding: ItemIku3Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Iku3Model){
            with(binding){
                tvNama.text = data.nama
                tvNoInduk.text = StringBuilder("${data.noInduk} ( ${data.namaProdi} )")
                if (data.kegiatanTriDharma != null) tvKegiatanTridharma.text = StringBuilder(" - ${data.kegiatanTriDharma.namaKegiatan}") else tvKegiatanTridharma.visibility = GONE
                if (data.praktisi != null) tvPraktisi.text = StringBuilder(" - ${data.praktisi.jenisPekerjaan}") else tvPraktisi.visibility = GONE
                if (data.prestasiMembimbingMahasiswa != null) tvPrestasiMembimbing.text = StringBuilder(" - ${data.prestasiMembimbingMahasiswa.namaLomba}") else tvPrestasiMembimbing.visibility = GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Iku3ViewHolder {
        val binding = ItemIku3Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Iku3ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Iku3ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size
}