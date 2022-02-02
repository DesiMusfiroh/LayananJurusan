package com.layanan.jurusan.ui.iku

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.layanan.jurusan.data.model.Iku3Model
import com.layanan.jurusan.databinding.ActivityIku3Binding
import com.layanan.jurusan.databinding.DialogIku3Binding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class Iku3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIku3Binding
    private lateinit var viewModel: IkuViewModel
    private lateinit var adapter: Iku3Adapter
    companion object {
        const val EXTRA_YEAR = "year"
    }
    private lateinit var dataResponse: List<Iku3Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIku3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[IkuViewModel::class.java]

        val extras = intent.extras
        val year = extras?.getString(EXTRA_YEAR,"0")
        Log.d("ExtraTahun",year!!)
        populateIku3(year)
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun populateIku3(year: String) {
        viewModel.getIku3(year).observe(this,{
            Log.d("Iku3Activity",it.toString())
            dataResponse = it
            adapter = Iku3Adapter(dataResponse)
            adapter.notifyDataSetChanged()

            with(binding){
                rvIku3.layoutManager = LinearLayoutManager(this@Iku3Activity)
                rvIku3.setHasFixedSize(true)
                rvIku3.adapter = adapter
            }
            adapter.setOnItemClickCallback(object : Iku3Adapter.OnItemClickCallback {
                override fun onItemClicked(data: Iku3Model) {
                    showDialogData(data)
                }
            })
        })
    }

    private fun showDialogData(data: Iku3Model) {
        val dialog = BottomSheetDialog(this)
        val dialogIku3Binding = DialogIku3Binding.inflate(layoutInflater)

        dialogIku3Binding.apply {
            tvNama.text = data.nama
            tvProdi.text = data.namaProdi

            tvJenisKegiatan.text = data.kegiatanTriDharma?.jenisKegiatan
            tvNamaKegiatan.text = data.kegiatanTriDharma?.namaKegiatan
            tvNamaKampus.text = data.kegiatanTriDharma?.namaKampus
            tvTipeKampus.text = data.kegiatanTriDharma?.tipeKampus
            tvSkRektorKegiatan.text = data.kegiatanTriDharma?.noSk

            tvJenisPekerjaan.text = data.praktisi?.jenisPekerjaan
            tvJenisPerusahaan.text = data.praktisi?.jenisPerusahaan
            tvPersetujuan.text = data.praktisi?.persetujuan

            tvJuara.text = data.prestasiMembimbingMahasiswa?.juara
            tvNamaLomba.text = data.prestasiMembimbingMahasiswa?.namaLomba
            tvTipeLomba.text = data.prestasiMembimbingMahasiswa?.tipeLomba
            tvNamaMahasiswa.text = data.prestasiMembimbingMahasiswa?.namaMahasiswa
            tvNim.text = data.prestasiMembimbingMahasiswa?.nimMahasiswa

            if (data.kegiatanTriDharma?.jenisKegiatan == null) tableKegiatanTridharma.visibility =
                View.GONE
            if (data.praktisi?.jenisPekerjaan == null) tablePraktisi.visibility = View.GONE
            if (data.prestasiMembimbingMahasiswa?.namaLomba == null) tablePrestasiMembimbing.visibility =
                View.GONE

            btnBukti.setOnClickListener {
                val url = data.bukti
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogIku3Binding.root)
        dialog.show()
    }
}