package com.layanan.jurusan.ui.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.databinding.ActivityListNewsBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class ListNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListNewsBinding
    private lateinit var newsAdapter: ListNewsAdapter
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Berita"

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        populateNews()
    }

    private fun populateNews() {
        binding.apply {
            newsAdapter = ListNewsAdapter()
            rvNews.setHasFixedSize(true)
            rvNews.adapter = newsAdapter.withLoadStateHeaderAndFooter(
                header = NewsLoadStateAdapter{ newsAdapter.retry() },
                footer = NewsLoadStateAdapter { newsAdapter.retry() }
            )
            rvNews.layoutManager =  GridLayoutManager(this@ListNewsActivity, 2)
        }
        viewModel.getListNews().observe(this, {
            if (it != null) {
                newsAdapter.submitData(lifecycle, it)
            } else {
                binding.rvNews.visibility = View.GONE
            }
        })

        newsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: NewsModel) {
                val detailIntent = Intent(this@ListNewsActivity, NewsActivity::class.java)
                detailIntent.putExtra(NewsActivity.EXTRA_NEWS, data.id)
                startActivity(detailIntent)
            }
        })
    }
}