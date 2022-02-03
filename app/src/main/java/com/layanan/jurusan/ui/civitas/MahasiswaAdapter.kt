package com.layanan.jurusan.ui.civitas

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Mahasiswa
import com.layanan.jurusan.databinding.ItemMahasiswaBinding

class MahasiswaAdapter(private val list: List<Mahasiswa>) : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Mahasiswa)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val binding = ItemMahasiswaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MahasiswaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            @Suppress("DEPRECATION")
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size

    class MahasiswaViewHolder(private val binding: ItemMahasiswaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Mahasiswa) {
            with(binding) {
                tvName.text = data.nama
                tvNim.text = data.nim
//                Glide.with(itemView.context)
//                        .load(data.)
//                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
//                        .into(imgItemPhoto)
            }
        }
    }
}