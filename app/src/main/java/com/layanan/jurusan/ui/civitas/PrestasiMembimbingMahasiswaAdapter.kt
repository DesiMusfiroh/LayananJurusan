package com.layanan.jurusan.ui.civitas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.PraktisiModel
import com.layanan.jurusan.data.model.PrestasiMembimbingMahasiswa

class PrestasiMembimbingMahasiswaAdapter(private val context: Context, private val list: ArrayList<PrestasiMembimbingMahasiswa>) : BaseAdapter(){
    private lateinit var namaLomba: TextView
    private lateinit var tipeLomba: TextView
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

        convertView = LayoutInflater.from(context).inflate(R.layout.item_prestasi_membimbing_mahasiswa, parent, false)
        namaLomba = convertView.findViewById(R.id.tv_prestasi_membimbing_nama_lomba)
        namaLomba.text = data.namaLomba
        tipeLomba = convertView.findViewById(R.id.tv_prestasi_membimbing_tipe_lomba)
        tipeLomba.text = data.tipeLomba
        return convertView
    }

}