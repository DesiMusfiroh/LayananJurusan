package com.layanan.jurusan.ui.civitas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.KegiatanTriDharmaModel
import com.layanan.jurusan.data.model.PraktisiModel

class PraktisiAdapter(private val context: Context, private val list: ArrayList<PraktisiModel>) : BaseAdapter(){
    private lateinit var jenisPekerjaan: TextView
    private lateinit var jenisPerusahaan: TextView
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

        convertView = LayoutInflater.from(context).inflate(R.layout.item_praktisi, parent, false)
        jenisPekerjaan = convertView.findViewById(R.id.tv_praktisi_jenis_pekerjaan)
        jenisPekerjaan.text = data.jenisPekerjaan
        jenisPerusahaan = convertView.findViewById(R.id.tv_praktisi_jenis_perusahaan)
        jenisPerusahaan.text = data.jenisPerusahaan
        return convertView
    }

}