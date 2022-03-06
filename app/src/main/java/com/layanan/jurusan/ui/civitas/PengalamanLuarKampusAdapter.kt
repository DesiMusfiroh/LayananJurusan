package com.layanan.jurusan.ui.civitas

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.PengalamanLuarKampusModel

class PengalamanLuarKampusAdapter(private val context: Context, private val list: ArrayList<PengalamanLuarKampusModel>) : BaseAdapter(){
    private lateinit var pengalamanLuarKampus: TextView
    private lateinit var namaTempatKegiatan: TextView
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.item_pengalaman_luar_kampus, parent, false)
        pengalamanLuarKampus = convertView.findViewById(R.id.tv_pengalaman_luar_kampus)
        namaTempatKegiatan = convertView.findViewById(R.id.tv_nama_tempat_kegiatan)

        namaTempatKegiatan.text = list[position].namaTempat
        pengalamanLuarKampus.text = list[position].jenisKegiatan
        return convertView
    }

}