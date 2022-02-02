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
import com.layanan.jurusan.data.model.Iku2Model
import com.layanan.jurusan.databinding.ActivityIku2Binding
import com.layanan.jurusan.databinding.DialogIku2Binding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class Iku2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIku2Binding
    private lateinit var viewModel: IkuViewModel
    private lateinit var adapter: Iku2Adapter
    companion object {
        const val EXTRA_YEAR = "year"
    }
    private lateinit var dataResponse: List<Iku2Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIku2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[IkuViewModel::class.java]

        val extras = intent.extras
        val year = extras?.getString(EXTRA_YEAR,"0")
        Log.d("ExtraTahun",year!!)
        populateIku2(year)
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun populateIku2(year: String) {
        viewModel.getIku2(year).observe(this,{
            Log.d("Iku2Activity",it.toString())
            dataResponse = it
            adapter = Iku2Adapter(dataResponse)

            adapter.notifyDataSetChanged()

            with(binding){
                rvIku2.layoutManager = LinearLayoutManager(this@Iku2Activity)
                rvIku2.setHasFixedSize(true)
                rvIku2.adapter = adapter
            }
            adapter.setOnItemClickCallback(object : Iku2Adapter.OnItemClickCallback {
                override fun onItemClicked(data: Iku2Model) {
                    showDialogData(data)
                }
            })
        })
    }

    private fun showDialogData(data: Iku2Model) {
        val dialog = BottomSheetDialog(this)
        val dialogIku2Binding = DialogIku2Binding.inflate(layoutInflater)

        dialogIku2Binding.apply {
            tvNama.text = data.nama
            tvProdi.text = data.namaProdi
            tvNim.text = data.nim

            tvSks.text = data.pengalamanLuarKampus?.sks
            tvJenisKegiatan.text = data.pengalamanLuarKampus?.jenisKegiatan
            tvNamaTempatKegiatan.text = data.pengalamanLuarKampus?.namaTempat
            tvDosenPembimbingKegiatan.text = data.pengalamanLuarKampus?.dosenPembimbing
            tvSkRektorKegiatan.text = data.pengalamanLuarKampus?.noSk
            tvJuara.text = data.prestasi?.juara
            tvNamaLomba.text = data.prestasi?.namaLomba
            tvPenyelenggara.text = data.prestasi?.penyelenggara
            tvDosenPembimbingPrestasi.text = data.prestasi?.dosenPembimbing

            if (data.pengalamanLuarKampus?.jenisKegiatan == null) tablePengalamanLuarKampus.visibility = View.GONE
            if (data.prestasi?.namaLomba == null) tablePrestasi.visibility = View.GONE

            btnBukti.setOnClickListener {
                val url = data.bukti
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogIku2Binding.root)
        dialog.show()
    }
}