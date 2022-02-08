package com.layanan.jurusan.ui.civitas

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.PrestasiModel

class PrestasiMahasiswaAdapter(private val context: Context, private val arrayList: java.util.ArrayList<PrestasiModel>) : BaseAdapter() {
    private lateinit var prestasi: TextView
    private lateinit var penyelenggara: TextView

    override fun getCount(): Int =arrayList.size

    override fun getItem(position: Int): Any = position

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.item_prestasi_mahasiswa, parent, false)
        prestasi = convertView.findViewById(R.id.tv_prestasi)
        penyelenggara = convertView.findViewById(R.id.tv_penyelenggara)
        prestasi.text = StringBuilder("${arrayList[position].juara} - ${arrayList[position].namaLomba}")
        penyelenggara.text = arrayList[position].penyelenggara
        return convertView
    }
}
