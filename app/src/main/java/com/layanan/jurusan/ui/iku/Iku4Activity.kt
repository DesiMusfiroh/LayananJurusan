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
import com.layanan.jurusan.data.model.Iku4Model
import com.layanan.jurusan.databinding.ActivityIku4Binding
import com.layanan.jurusan.databinding.DialogIku4Binding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class Iku4Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIku4Binding
    private lateinit var viewModel: IkuViewModel
    private lateinit var adapter: Iku4Adapter
    companion object {
        const val EXTRA_YEAR = "year"
    }
    private lateinit var dataResponse: List<Iku4Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIku4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[IkuViewModel::class.java]

        val extras = intent.extras
        val year = extras?.getString(EXTRA_YEAR,"0")
        Log.d("ExtraTahun",year!!)
        val tvIkuTitle: String = binding.tvIkuTitle.text.toString()
        binding.tvIkuTitle.text = "$tvIkuTitle - $year"
        populateIku4(year)
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun populateIku4(year: String) {
        viewModel.getIku4(year).observe(this,{
            Log.d("Iku4Activity",it.toString())
            dataResponse = it
            adapter = Iku4Adapter(dataResponse)
            adapter.notifyDataSetChanged()

            with(binding){
                rvIku4.layoutManager = LinearLayoutManager(this@Iku4Activity)
                rvIku4.setHasFixedSize(true)
                rvIku4.adapter = adapter
            }
            adapter.setOnItemClickCallback(object : Iku4Adapter.OnItemClickCallback {
                override fun onItemClicked(data: Iku4Model) {
                    showDialogData(data)
                }
            })
        })
    }

    private fun showDialogData(data: Iku4Model) {
        val dialog = BottomSheetDialog(this)
        val dialogIku4Binding = DialogIku4Binding.inflate(layoutInflater)

        dialogIku4Binding.apply {
            tvNama.text = data.nama
            tvProdi.text = data.namaProdi
            tvNoInduk.text = data.noInduk

            tvProdiLulus.text = data.berkualifikasiS3?.prodiLulus
            tvPtLulus.text = data.berkualifikasiS3?.ptLulus
            tvNamaPt.text = data.berkualifikasiS3?.namaPt
            tvNomorIjazah.text = data.berkualifikasiS3?.noIjazah

            tvJenisSertifikat.text = data.sertifikatKompetensi?.jenisSertifikat
            tvNomorSertifikat.text = data.sertifikatKompetensi?.nomorSertifikat
            tvNamaLembaga.text = data.sertifikatKompetensi?.namaLembaga

            tvJenisPekerjaan.text = data.praktisiProfesional?.jenisPekerjaan
            tvJenisPerusahaan.text = data.praktisiProfesional?.jenisPerusahaan
            tvNamaPerusahaan.text = data.praktisiProfesional?.namaPerusahaan

            if (data.berkualifikasiS3?.ptLulus == null) tableBerkualifikasiS3.visibility = View.GONE
            if (data.sertifikatKompetensi?.namaLembaga == null) tableSertifikatKompetensi.visibility = View.GONE
            if (data.praktisiProfesional?.namaPerusahaan == null) tablePraktisiProfesional.visibility = View.GONE

            btnBukti.setOnClickListener {
                val url = data.bukti
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogIku4Binding.root)
        dialog.show()
    }
}