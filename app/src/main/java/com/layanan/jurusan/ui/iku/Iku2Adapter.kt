package com.layanan.jurusan.ui.iku

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Iku1Model
import com.layanan.jurusan.data.model.Iku2Model
import com.layanan.jurusan.databinding.ItemIku1Binding
import com.layanan.jurusan.databinding.ItemIku2Binding

class Iku2Adapter(private val list: List<Iku2Model>) : RecyclerView.Adapter<Iku2Adapter.Iku2ViewHolder>() {
    class Iku2ViewHolder(private val binding: ItemIku2Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Iku2Model){
            with(binding){
                val no = position + 1
                tvNo.text = no.toString()
                tvNama.text = data.nama
                tvNim.text = data.nim
                tvJenjangProdi.text = data.jenjangProdi
                tvNamaProdi.text = data.namaProdi
                tvSks.text = data.pengalamanLuarKampus?.sks
                tvJenisKegiatan.text = data.pengalamanLuarKampus?.jenisKegiatan
                tvNamaTempatKegiatan.text = data.pengalamanLuarKampus?.namaTempat
                tvDosenPembimbing.text = data.pengalamanLuarKampus?.dosenPembimbing
                tvSkRektor.text = data.pengalamanLuarKampus?.noSk
                tvJuara.text = data.prestasi?.juara
                tvNamaLomba.text = data.prestasi?.namaLomba
                tvPenyelenggara.text = data.prestasi?.penyelenggara
                tvDosenPembimbingLomba.text = data.prestasi?.dosenPembimbing
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
    }

    override fun getItemCount(): Int {
        Log.d("CountData",list.size.toString())
        return list.size
    }
}