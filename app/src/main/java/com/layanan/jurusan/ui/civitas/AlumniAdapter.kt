package com.layanan.jurusan.ui.civitas

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Mahasiswa
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.databinding.ItemAlumniBinding
import com.layanan.jurusan.databinding.ItemNewsBinding
import com.layanan.jurusan.ui.news.ListNewsAdapter

class AlumniAdapter: PagingDataAdapter<Mahasiswa, AlumniAdapter.AlumniViewHolder>(DiffCallback) {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Mahasiswa>() {
            override fun areItemsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class AlumniViewHolder(val binding: ItemAlumniBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Mahasiswa){
            with(binding){
                tvName.text = data.nama
                tvNim.text = data.nim
                tvProdi.text = data.prodi?.nama
            }

            itemView.setOnClickListener {
                val intent =  Intent(itemView.context, MahasiswaActivity::class.java)
                intent.putExtra(MahasiswaActivity.EXTRA_MAHASISWA, data.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: AlumniViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumniViewHolder {
        val binding = ItemAlumniBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlumniViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.d("CountItem",super.getItemCount().toString())
        return super.getItemCount()
    }
}