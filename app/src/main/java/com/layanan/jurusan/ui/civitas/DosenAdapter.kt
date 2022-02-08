package com.layanan.jurusan.ui.civitas

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.DosenModel
import com.layanan.jurusan.databinding.ItemDosenBinding

class DosenAdapter(private val list: List<DosenModel>): RecyclerView.Adapter<DosenAdapter.DosenViewHolder>() {
    class DosenViewHolder(private val binding: ItemDosenBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DosenModel){
            with(binding){
                tvName.text = data.nama
                tvNoInduk.text = data.noInduk
                tvProdi.text = data.prodi?.nama
            }
            itemView.setOnClickListener{
                val intent =  Intent(itemView.context, DosenActivity::class.java)
                intent.putExtra(DosenActivity.EXTRA_DOSEN, data.id)
                itemView.context.startActivity(intent)
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
    }

    override fun getItemCount(): Int = list.size
}