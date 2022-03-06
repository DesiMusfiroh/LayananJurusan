package com.layanan.jurusan.ui.civitas

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.Mahasiswa
import com.layanan.jurusan.data.model.PengalamanLuarKampusModel
import com.layanan.jurusan.data.model.PrestasiModel
import com.layanan.jurusan.databinding.ActivityMahasiswaBinding
import com.layanan.jurusan.ui.news.NewsActivity
import com.layanan.jurusan.viewmodel.ViewModelFactory


class MahasiswaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMahasiswaBinding
    private lateinit var viewModel: MahasiswaViewModel
    private lateinit var dataMahasiswa: Mahasiswa
    var listPrestasi: ArrayList<PrestasiModel> = ArrayList()
    var listPengalamanLuarKampus: ArrayList<PengalamanLuarKampusModel> = ArrayList()
    var prestasiAdapter: PrestasiMahasiswaAdapter? = null
    var pengalamanLuarKampusAdapter: PengalamanLuarKampusAdapter? = null

    companion object {
        const val EXTRA_MAHASISWA = "mahasiswa"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MahasiswaViewModel::class.java]

        val extras = intent.extras
        Log.d("Extras",extras.toString())

        visibilityView(false)
        if (extras != null){
            val id = extras.getInt(EXTRA_MAHASISWA,0)
            viewModel.setId(id)
            viewModel.getDetailMahasiswa().observe(this,{
                Log.d("DataMahasiswa","Siap")
                setUpView(it)
            })
        }

    }

    private fun setUpView(data: Mahasiswa){
        binding.shimmerTvName.stopShimmer()
        binding.shimmerTvNim.stopShimmer()
        binding.tvName.text = data.nama
        binding.tvProdi.text = data.prodi?.nama
        binding.tvNim.text = data.nim
        binding.tvStatus.text = data.statusKuliah
        binding.tvAngkatan.text = data.angkatan
        binding.tvDosenPa.text = data.dosen.namaGelar

        binding.tvName.visibility = View.VISIBLE
        binding.tvNim.visibility = View.VISIBLE
        binding.shimmerTvNim.visibility = View.GONE
        binding.shimmerTvName.visibility = View.GONE

        for (item in data.prestasi!!){
            listPrestasi.add(item)
        }

        for (item in data.pengalamanLuarKampus!!){
            listPengalamanLuarKampus.add(item)
        }


        prestasiAdapter = PrestasiMahasiswaAdapter(this, listPrestasi)
        binding.listPrestasi.adapter = prestasiAdapter
        pengalamanLuarKampusAdapter = PengalamanLuarKampusAdapter(this,listPengalamanLuarKampus)
        binding.listPengalamanLuarKampus.adapter = pengalamanLuarKampusAdapter

        binding.apply {
            cardPrestasi.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    if (cardListPrestasi.visibility == View.VISIBLE) {
                        TransitionManager.beginDelayedTransition(cardPrestasi, AutoTransition())
                        cardListPrestasi.visibility = View.GONE
                        iconArrowPrestasi.setImageResource(R.drawable.ic_arrow_next)
                    } else {
                        TransitionManager.beginDelayedTransition(cardPrestasi, AutoTransition())
                        cardListPrestasi.visibility = View.VISIBLE
                        iconArrowPrestasi.setImageResource(R.drawable.ic_arrow_to_bottom)
                    }
                }
            })

            cardPengalaman.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    if (cardListPengalaman.visibility == View.VISIBLE) {
                        TransitionManager.beginDelayedTransition(cardPengalaman, AutoTransition())
                        cardListPengalaman.visibility = View.GONE
                        iconArrowPengalaman.setImageResource(R.drawable.ic_arrow_next)
                    } else {
                        TransitionManager.beginDelayedTransition(cardPengalaman, AutoTransition())
                        cardListPengalaman.visibility = View.VISIBLE
                        iconArrowPengalaman.setImageResource(R.drawable.ic_arrow_to_bottom)
                    }
                }
            })
        }

        visibilityView(true)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    fun visibilityView(isVisible: Boolean){
        with(binding){
            if(isVisible){
                progressBar.visibility = View.GONE
                cvAdditionalInfo.visibility = View.VISIBLE
                tableLayoutMahasiswa.visibility = View.VISIBLE
            }else{
                progressBar.visibility = View.VISIBLE
                cvAdditionalInfo.visibility = View.GONE
                tableLayoutMahasiswa.visibility = View.GONE
            }

        }
    }
}