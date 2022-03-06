package com.layanan.jurusan.ui.mail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.layanan.jurusan.R

class JenisTandaTanganSpinnerAdapter(context: Context, resource: Int, spinnerText: Int, val listJenisTtd: List<JenisTtd>) : ArrayAdapter<JenisTtd>(context, resource, spinnerText, listJenisTtd) {
    override fun getItem(position: Int): JenisTtd? {
        return listJenisTtd[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position)
    }

    private fun initView(position: Int): View {
        val jenisTtd: JenisTtd? = getItem(position)

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = inflater.inflate(R.layout.item_spinner, null)
        val textView = v.findViewById<TextView>(R.id.tv_item_spinner)
        textView.text = jenisTtd?.name
        return v

    }
}