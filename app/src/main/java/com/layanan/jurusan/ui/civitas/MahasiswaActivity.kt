package com.layanan.jurusan.ui.civitas

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.PrestasiModel
import com.layanan.jurusan.databinding.ActivityMahasiswaBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory


class MahasiswaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMahasiswaBinding
    private lateinit var viewModel: MahasiswaViewModel
    var listPrestasi: ArrayList<PrestasiModel> = ArrayList()
    var prestasiAdapter: PrestasiMahasiswaAdapter? = null

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

        // dummy
        listPrestasi.add(PrestasiModel(1, "Juara 2", "Nasional","Lomba Web Dev", "Kemendikbud", "" ))
        listPrestasi.add(PrestasiModel(2, "Juara 1", "Nasional","Lomba A", "Kemendikbud", "" ))

        prestasiAdapter = PrestasiMahasiswaAdapter(this, listPrestasi)
        binding.listPrestasi.adapter = prestasiAdapter

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
        }

    }
}