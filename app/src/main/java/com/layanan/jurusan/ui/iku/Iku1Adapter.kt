package com.layanan.jurusan.ui.iku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Iku1Model
import com.layanan.jurusan.databinding.ItemIku1Binding

@Suppress("DEPRECATION")
class Iku1Adapter(private val list: List<Iku1Model>) : RecyclerView.Adapter<Iku1Adapter.Iku1ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Iku1Model)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class Iku1ViewHolder(private val binding: ItemIku1Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Iku1Model){
            with(binding){
                tvNama.text = data.nama
                tvProdi.text = StringBuilder("${data.nim} ( ${data.jenjangProdi} - ${data.namaProdi} )")
                tvIjazah.text = StringBuilder("Ijasah : ${data.noIjazah} - ${data.tanggalIjazah}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Iku1ViewHolder {
        val binding = ItemIku1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Iku1ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Iku1ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size

}