package com.layanan.jurusan.ui.civitas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.PrestasiMembimbingMahasiswa
import com.layanan.jurusan.data.model.SertifikatKompetensi

class SertifikatKompetensiAdapter(private val context: Context, private val list: ArrayList<SertifikatKompetensi>) : BaseAdapter(){
    private lateinit var namaLembaga: TextView
    private lateinit var jenisSertifikat: TextView
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

        convertView = LayoutInflater.from(context).inflate(R.layout.item_sertifikat_kompetensi, parent, false)
        namaLembaga = convertView.findViewById(R.id.tv_sertifikat_kompetensi_nama_lembaga)
        namaLembaga.text = data.namaLembaga
        jenisSertifikat = convertView.findViewById(R.id.tv_sertifikat_kompetensi_jenis_sertifikat)
        jenisSertifikat.text = data.jenisSertifikat
        return convertView
    }

}