package com.layanan.jurusan.ui.iku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Iku1Model
import com.layanan.jurusan.data.model.MatriksIkuModel
import com.layanan.jurusan.databinding.ItemIku1Binding
import com.layanan.jurusan.databinding.ItemMatriksIkuBinding

class MatriksIkuAdapter(private val list: List<MatriksIkuModel>) : RecyclerView.Adapter<MatriksIkuAdapter.MatriksIkuViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: MatriksIkuModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class MatriksIkuViewHolder(private val binding: ItemMatriksIkuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: MatriksIkuModel){
            with(binding){
                titleIku.text = data.iku
                descIku.text = data.judul
                capaian.text = data.capaian.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatriksIkuViewHolder {
        val binding = ItemMatriksIkuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatriksIkuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatriksIkuViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}