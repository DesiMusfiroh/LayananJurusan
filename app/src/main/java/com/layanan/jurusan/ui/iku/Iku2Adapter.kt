package com.layanan.jurusan.ui.iku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Iku2Model
import com.layanan.jurusan.databinding.ItemIku2Binding

class Iku2Adapter(private val list: List<Iku2Model>) : RecyclerView.Adapter<Iku2Adapter.Iku2ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Iku2Model)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class Iku2ViewHolder(private val binding: ItemIku2Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Iku2Model){
            with(binding){
                tvNama.text = data.nama
                tvProdi.text = StringBuilder("${data.nim} ( ${data.jenjangProdi} - ${data.namaProdi} )")
                if (data.pengalamanLuarKampus != null) tvPengalamanLuarKampus.text = StringBuilder(" - ${data.pengalamanLuarKampus.namaTempat}")
                else tvPengalamanLuarKampus.isVisible = false
                if (data.prestasi != null) tvPrestasi.text = StringBuilder(" - ${data.prestasi.namaLomba}")
                else tvPrestasi.isVisible = false

//                val no = position + 1
//                tvNo.text = no.toString()
//                tvNama.text = data.nama
//                tvNim.text = data.nim
//                tvJenjangProdi.text = data.jenjangProdi
//                tvNamaProdi.text = data.namaProdi
//                tvSks.text = data.pengalamanLuarKampus?.sks
//                tvJenisKegiatan.text = data.pengalamanLuarKampus?.jenisKegiatan
//                tvNamaTempatKegiatan.text = data.pengalamanLuarKampus?.namaTempat
//                tvDosenPembimbing.text = data.pengalamanLuarKampus?.dosenPembimbing
//                tvSkRektor.text = data.pengalamanLuarKampus?.noSk
//                tvJuara.text = data.prestasi?.juara
//                tvNamaLomba.text = data.prestasi?.namaLomba
//                tvPenyelenggara.text = data.prestasi?.penyelenggara
//                tvDosenPembimbingLomba.text = data.prestasi?.dosenPembimbing
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Iku2ViewHolder {
        val binding = ItemIku2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Iku2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Iku2ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size
}