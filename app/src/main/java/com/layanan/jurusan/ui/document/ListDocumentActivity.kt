package com.layanan.jurusan.ui.document

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.layanan.jurusan.databinding.ActivityListDocumentBinding

class ListDocumentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListDocumentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDocumentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}
