package com.layanan.jurusan.ui.iku

import android.app.DatePickerDialog
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.Iku1Model
import com.layanan.jurusan.databinding.ActivityIku1Binding
import com.layanan.jurusan.databinding.DialogIku1Binding
import com.layanan.jurusan.viewmodel.ViewModelFactory
import java.lang.StringBuilder
import java.text.DecimalFormat
import java.util.*


class Iku1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIku1Binding
    private lateinit var viewModel: IkuViewModel
    private lateinit var adapter: Iku1Adapter
    private lateinit var dataResponse: List<Iku1Model>

    companion object {
        const val EXTRA_YEAR = "year"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIku1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[IkuViewModel::class.java]

        val extras = intent.extras
        val year = extras?.getString(EXTRA_YEAR,"0")
        Log.d("ExtraTahun",year!!)
        populateIku1(year)
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun populateIku1(year: String) {
        viewModel.getIku1(year).observe(this, { data ->
            Log.d("DataActivityIku",data.toString())
            dataResponse = data
            if (data !== null) {
                adapter = Iku1Adapter(data)
                adapter.notifyDataSetChanged()

                binding.apply {
                    rvItem.layoutManager = LinearLayoutManager(this@Iku1Activity)
                    rvItem.setHasFixedSize(true)
                    rvItem.adapter = adapter
                }
                adapter.setOnItemClickCallback(object : Iku1Adapter.OnItemClickCallback {
                    override fun onItemClicked(data: Iku1Model) {
                        showDialogData(data)
                    }
                })
            }
        })
    }

    @SuppressLint("InflateParams")
    private fun showDialogData(data: Iku1Model) {
        val dialog = BottomSheetDialog(this)
        val dialogIku1Binding = DialogIku1Binding.inflate(layoutInflater)

        dialogIku1Binding.apply {
            tvNama.text = data.nama
            tvProdi.text = data.namaProdi
            tvNim.text = data.nim
            tvNomorIjazah.text = data.noIjazah
            tvTanggalIjazah.text = data.tanggalIjazah

            val umk = if (data.mendapatPekerjaan?.umk == null) "-" else "Rp. ${DecimalFormat("#,####").format(data.mendapatPekerjaan.umk)}"
            val penghasilan = if (data.mendapatPekerjaan?.penghasilan == null) "-" else DecimalFormat("#,####").format(data.mendapatPekerjaan?.penghasilan).toString()
            val presentaseDariUmk = if (data.mendapatPekerjaan?.presentaseDariUmk == null) "-" else "${data.mendapatPekerjaan?.presentaseDariUmk.toString()} %"
            tvMasaTungguKerja.text = data.mendapatPekerjaan?.masaTunggu
            tvKotaTempatKerja.text = data.mendapatPekerjaan?.kabKotaTempatKerja
            tvUmkKotaKerja.text = umk
            tvPenghasilanKerja.text = StringBuilder("Rp. $penghasilan ( $presentaseDariUmk dari UMK )")
            tvKriteriaPerusahaan.text = data.mendapatPekerjaan?.kriteriaPerusahaan
            tvNamaPerusahaan.text = data.mendapatPekerjaan?.namaPerusahaan
            tvPerjanjianKerja.text = data.mendapatPekerjaan?.perjanjianKerja
            tvIzinUsahaKerja.text = data.mendapatPekerjaan?.izinUsaha

            val umkKotaUsaha = if (data.wiraswasta?.umk == null) "-" else DecimalFormat("#,####").format(data.wiraswasta?.umk).toString()
            val penghasilanUsaha = if (data.wiraswasta?.penghasilan == null) "-" else DecimalFormat("#,####").format(data.wiraswasta?.penghasilan).toString()
            val presentaseDariUmkUsaha = if (data.wiraswasta?.presentaseDariUmk == null) "-" else DecimalFormat("#,####").format(data.wiraswasta?.presentaseDariUmk).toString()
            tvMasaTungguUsaha.text = data.wiraswasta?.masaTunggu
            tvKotaTempatUsaha.text = data.wiraswasta?.kabKotaTempatKerja
            tvUmkKotaUsaha.text = StringBuilder("Rp. $umkKotaUsaha")
            tvPenghasilanUsaha.text = StringBuilder("Rp. $penghasilanUsaha ( $presentaseDariUmkUsaha dari UMK )")
            tvKriteriaKewiraswastaan.text = data.wiraswasta?.kriteriaKewiraswastaan
            tvKriteriaUsaha.text = data.wiraswasta?.kriteriaUsaha
            tvNamaUsaha.text = data.wiraswasta?.namaUsaha
            tvIzinUsahaKewiraswastaan.text = data.wiraswasta?.izinUsaha

            tvMasaTungguStudi.text = data.melanjutkanStudi?.masaTunggu
            tvJenjangProdiLanjutan.text = data.melanjutkanStudi?.jenjangProdiLanjutan
            tvNamaProdi.text = data.melanjutkanStudi?.namaProdi
            tvNamaPerguruanTinggi.text = data.melanjutkanStudi?.namaPerguruanTinggi

            if (data.mendapatPekerjaan?.namaPerusahaan == null) tableMendapatPekerjaan.visibility = GONE
            if (data.wiraswasta?.namaUsaha == null) tableWiraswasta.visibility = GONE
            if (data.melanjutkanStudi?.namaPerguruanTinggi == null) tableMelanjutkanStudi.visibility = GONE

            btnBukti.setOnClickListener {
                val url = data.bukti
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(dialogIku1Binding.root)
        dialog.show()
    }
}