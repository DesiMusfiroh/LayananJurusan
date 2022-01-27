package com.layanan.jurusan.ui.mail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.layanan.jurusan.data.model.JenisSuratModel
import com.layanan.jurusan.databinding.ActivityFormSuratBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class FormSuratActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormSuratBinding
    private lateinit var viewModel: FormSuratViewModel
    companion object {
        const val EXTRA_MAIL = "mail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormSuratBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[FormSuratViewModel::class.java]

        val mail = intent.getParcelableExtra<JenisSuratModel>(EXTRA_MAIL) as JenisSuratModel
        binding.tvTitle.text = mail.judul

    }
}