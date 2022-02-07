package com.layanan.jurusan.ui.civitas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.DosenModel
import com.layanan.jurusan.data.model.Mahasiswa
import com.layanan.jurusan.databinding.ItemDosenBinding
import com.layanan.jurusan.databinding.ItemMahasiswaBinding

class DosenAdapter(private val list: List<DosenModel>): RecyclerView.Adapter<DosenAdapter.DosenViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: DosenModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class DosenViewHolder(private val binding: ItemDosenBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DosenModel){
            with(binding){
                tvName.text = data.nama
                tvNoInduk.text = data.noInduk
                tvProdi.text = data.prodi?.nama
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DosenViewHolder {
        val binding = ItemDosenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DosenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DosenViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            @Suppress("DEPRECATION")
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}