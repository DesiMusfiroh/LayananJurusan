package com.layanan.jurusan.ui.iku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Iku4Model
import com.layanan.jurusan.databinding.ItemIku4Binding

class Iku4Adapter(private val list: List<Iku4Model>) : RecyclerView.Adapter<Iku4Adapter.Iku4ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Iku4Model)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class Iku4ViewHolder(private val binding: ItemIku4Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Iku4Model){
            with(binding){
                tvNama.text = data.nama
                tvNoInduk.text = StringBuilder("${data.noInduk} ( ${data.namaProdi} )")
                if (data.berkualifikasiS3 != null) tvBerkualifikasiS3.text = StringBuilder(" - ${data.berkualifikasiS3.namaPt}") else tvBerkualifikasiS3.visibility = View.GONE
                if (data.sertifikatKompetensi != null) tvSertifikatKompetensi.text = StringBuilder(" - ${data.sertifikatKompetensi.namaLembaga}") else tvSertifikatKompetensi.visibility = View.GONE
                if (data.praktisiProfesional != null) tvPraktisiProfesional.text = StringBuilder(" - ${data.praktisiProfesional.namaPerusahaan}") else tvPraktisiProfesional.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Iku4ViewHolder {
        val binding = ItemIku4Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Iku4ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Iku4ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size
}