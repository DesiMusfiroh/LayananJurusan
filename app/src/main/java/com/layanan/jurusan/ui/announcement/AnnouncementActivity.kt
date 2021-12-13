package com.layanan.jurusan.ui.announcement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.layanan.jurusan.R
import com.layanan.jurusan.databinding.ActivityAnnouncementBinding
import com.layanan.jurusan.databinding.ActivityJurusanBinding

class AnnouncementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnnouncementBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}