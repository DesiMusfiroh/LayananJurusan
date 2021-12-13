package com.layanan.jurusan.ui.jurusan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.layanan.jurusan.databinding.ActivityJurusanBinding

class JurusanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJurusanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJurusanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}