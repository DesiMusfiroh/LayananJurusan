package com.layanan.jurusan.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.layanan.jurusan.R
import com.layanan.jurusan.databinding.ActivityTestNewsBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TestNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestNewsBinding
    private lateinit var newsAdapter: TestNewsAdapter
    private lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        setupRecyclerView()
        loadData()
//        binding.rvNews.adapter = newsAdapter.withLoadStateFooter(
//            footer = TestLoadStateAdapter{newsAdapter.retry()}
//        )

//        newsAdapter.addLoadStateListener {
//            binding.progressBar.isVisible = it.source.refresh is LoadState.Loading
//            binding.rvNews.isVisible = it.source.refresh is LoadState.NotLoading
//
//            // No results found
//            if (it.source.refresh is LoadState.NotLoading &&
//                it.append.endOfPaginationReached &&
//                newsAdapter.itemCount < 1
//            ) {
//                binding.rvNews.isVisible = false
//                binding.tvNoSearchResult.isVisible = true
//            } else {
//                binding.tvNoSearchResult.isVisible = false
//            }
//        }

//        binding.rvNews.layoutManager = LinearLayoutManager(this)
//
//        viewModel.testNews.observe(this,{
//            lifecycleScope.launch {
//                Log.d("TestNewsActivity",it.toString())
//                newsAdapter.submitData(it)
//            }
//        })
//
//        binding.btnRetry.setOnClickListener{
//            newsAdapter.retry()
//        }

    }

    fun setupRecyclerView(){
        newsAdapter = TestNewsAdapter()
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@TestNewsActivity)
            setHasFixedSize(true)
        }
    }

    fun loadData(){
        lifecycleScope.launch {
            viewModel.testNews.collectLatest {
                newsAdapter.submitData(it)
            }
        }
    }
}