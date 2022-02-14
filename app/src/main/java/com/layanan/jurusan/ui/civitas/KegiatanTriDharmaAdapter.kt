package com.layanan.jurusan.ui.civitas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.KegiatanTriDharmaModel

class KegiatanTriDharmaAdapter(private val context: Context, private val list: ArrayList<KegiatanTriDharmaModel>) : BaseAdapter(){
    private lateinit var namaKegiatan: TextView
    private lateinit var namaKampus: TextView
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val data = list[position]

        convertView = LayoutInflater.from(context).inflate(R.layout.item_kegiatan_tri_dharma, parent, false)
        namaKampus = convertView.findViewById(R.id.tv_tridharma_nama_kampus)
        namaKampus.text = data.namaKampus
        namaKegiatan = convertView.findViewById(R.id.tv_tridharma_nama_kegiatan)
        namaKegiatan.text = data.namaKegiatan
        return convertView

    }

}