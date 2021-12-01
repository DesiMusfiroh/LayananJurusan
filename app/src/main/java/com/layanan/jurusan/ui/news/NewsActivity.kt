package com.layanan.jurusan.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.layanan.jurusan.R
import com.layanan.jurusan.databinding.ActivityNewsBinding
import com.layanan.jurusan.databinding.ItemNewsBinding

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var viewModel: NewsViewModel

    companion object {
        const val EXTRA_NEWS = "news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        supportActionBar?.hide()
    }
}