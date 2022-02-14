package com.layanan.jurusan.ui.mail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.model.TujuanSuratModel
import com.layanan.jurusan.databinding.ItemRiwayatSuratDosenBinding

class ListRiwayatSuratDosenAdapter : PagingDataAdapter<TujuanSuratModel, ListRiwayatSuratDosenAdapter.ListRiwayatSuratDosenViewHolder>(DiffCallback){
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<TujuanSuratModel>() {
            override fun areItemsTheSame(oldItem: TujuanSuratModel, newItem: TujuanSuratModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TujuanSuratModel, newItem: TujuanSuratModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: TujuanSuratModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListRiwayatSuratDosenViewHolder(private val binding: ItemRiwayatSuratDosenBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TujuanSuratModel){
            with(binding){
                tvName.text = data.riwayatSurat?.user?.mahasiswa?.nama
                tvTitle.text = data.riwayatSurat?.jenisSurat?.judul
                tvDatetime.text = data.riwayatSurat?.tanggalPengajuan
                tvStatus.text = data.riwayatSurat?.status
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context,RiwayatSuratActivity::class.java)
                intent.putExtra(RiwayatSuratActivity.EXTRA_MAIL, data.riwayatSurat?.id)
                itemView.context.startActivity(intent)
            }

        }
    }

    override fun onBindViewHolder(holder: ListRiwayatSuratDosenViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListRiwayatSuratDosenViewHolder {
        val binding = ItemRiwayatSuratDosenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListRiwayatSuratDosenViewHolder(binding)
    }

}