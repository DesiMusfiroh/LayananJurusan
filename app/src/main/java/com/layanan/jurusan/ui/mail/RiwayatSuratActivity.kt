package com.layanan.jurusan.ui.mail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.layanan.jurusan.databinding.ActivityRiwayatSuratBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class RiwayatSuratActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRiwayatSuratBinding
    private lateinit var viewModel: RiwayatSuratViewModel
    companion object {
        const val EXTRA_MAIL = "mail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatSuratBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[RiwayatSuratViewModel::class.java]


    }
}