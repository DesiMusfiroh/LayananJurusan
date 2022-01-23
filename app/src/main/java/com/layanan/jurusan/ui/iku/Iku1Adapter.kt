package com.layanan.jurusan.ui.iku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.layanan.jurusan.data.model.Iku1Model
import com.layanan.jurusan.databinding.ItemIku1Binding
import java.text.DecimalFormat

class Iku1Adapter(private val list: List<Iku1Model>) : RecyclerView.Adapter<Iku1Adapter.Iku1ViewHolder>() {
    class Iku1ViewHolder(private val binding: ItemIku1Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Iku1Model){
            with(binding){
                val no = position + 1
                tvNo.text = no.toString()
                tvNama.text = data.nama
                tvNim.text = data.nim
                tvJenjangProdi.text = data.jenjangProdi
                tvNamaProdi.text = data.namaProdi
                tvNomorIjazah.text = data.noIjazah
                tvTanggalIjazah.text = data.tanggalIjazah
                tvMasaTunggu.text = data.mendapatPekerjaan?.masaTunggu
                tvKotaTempatKerja.text = data.mendapatPekerjaan?.kabKotaTempatKerja


                val umk = if (data.mendapatPekerjaan?.umk == null) "" else DecimalFormat("#,####").format(data.mendapatPekerjaan?.umk).toString()
                tvUmk.text = umk
                val penghasilan = if (data.mendapatPekerjaan?.penghasilan == null) "" else DecimalFormat("#,####").format(data.mendapatPekerjaan?.penghasilan).toString()
                tvPenghasilan.text = penghasilan
                val presentaseDariUmk = if (data.mendapatPekerjaan?.presentaseDariUmk == null) "" else DecimalFormat("#,####").format(data.mendapatPekerjaan?.presentaseDariUmk).toString()
                tvPresentaseDariUmk.text = presentaseDariUmk
                tvKriteriaPerusahaan.text = data.mendapatPekerjaan?.kriteriaPerusahaan
                tvNamaPerusahaan.text = data.mendapatPekerjaan?.namaPerusahaan
                tvPerjanjianKerja.text = data.mendapatPekerjaan?.perjanjianKerja
                tvIjinUsaha.text = data.mendapatPekerjaan?.izinUsaha

                tvMasaTungguUsaha.text = data.wiraswasta?.masaTunggu
                tvKotaTempatUsaha.text = data.wiraswasta?.kabKotaTempatKerja
                val umkKotaUsaha = if (data.wiraswasta?.umk == null) "" else DecimalFormat("#,####").format(data.wiraswasta?.umk).toString()
                tvUmkKotaUsaha.text = umkKotaUsaha
                val penghasilanUsaha = if (data.wiraswasta?.penghasilan == null) "" else DecimalFormat("#,####").format(data.wiraswasta?.penghasilan).toString()
                tvPenghasilanUsaha.text = penghasilanUsaha
                val presentaseDariUmkUsaha = if (data.wiraswasta?.presentaseDariUmk == null) "" else DecimalFormat("#,####").format(data.wiraswasta?.presentaseDariUmk).toString()
                tvPresentaseDariUmkUsaha.text = presentaseDariUmkUsaha
                tvKriteriaKewiraswastaan.text = data.wiraswasta?.kriteriaKewiraswastaan
                tvKriteriaUsaha.text = data.wiraswasta?.kriteriaUsaha
                tvNamaUsaha.text = data.wiraswasta?.namaUsaha
                tvIjinUsahaWiraswasta.text = data.wiraswasta?.izinUsaha
                tvMasaTungguLanjutanStudi.text = data.melanjutkanStudi?.masaTunggu
                tvJenjangProdiLanjutan.text = data.melanjutkanStudi?.jenjangProdiLanjutan
                tvNamaProdiLanjutan.text = data.melanjutkanStudi?.namaProdi
                tvPerguruanTinggi.text = data.melanjutkanStudi?.namaPerguruanTinggi


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
    }

    override fun getItemCount(): Int {
        return list.size
    }
}