package com.layanan.jurusan.ui.civitas

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.HasilKerjaDosen
import com.layanan.jurusan.data.model.PraktisiModel

class HasilKerjaDosenAdapter(private val context: Context, private val list: ArrayList<HasilKerjaDosen>) : BaseAdapter(){
    private lateinit var judul: TextView
    private lateinit var jenisKegiatan: TextView
    private lateinit var imgOpenLink: ImageView
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

        convertView = LayoutInflater.from(context).inflate(R.layout.item_hasil_kerja_dosen, parent, false)
        judul = convertView.findViewById(R.id.tv_hasil_kerja_dosen_judul)
        judul.text = data.judul
        jenisKegiatan = convertView.findViewById(R.id.tv_hasil_kerja_dosen_jenis_kegiatan)
        jenisKegiatan.text = data.jenisKegiatan

        imgOpenLink = convertView.findViewById(R.id.img_open_link)
        imgOpenLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(data.link)
            convertView.context.startActivity(intent)
        }
        return convertView
    }

}