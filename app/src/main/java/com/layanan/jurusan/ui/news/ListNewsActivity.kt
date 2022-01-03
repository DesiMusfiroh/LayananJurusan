package com.layanan.jurusan.ui.news

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.layanan.jurusan.databinding.ActivityListNewsBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListNewsBinding
    private lateinit var newsAdapter: ListNewsAdapter
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        populateNews()
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.d("HasilKetik",p0!!)

                viewModel.setQuery(p0)
                lifecycleScope.launch {
                    viewModel.searchNews().collectLatest {
                        newsAdapter.notifyDataSetChanged()
                        newsAdapter.submitData(it)
                        closeKeyboard()
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.setQuery(p0!!)
                lifecycleScope.launch {
                    viewModel.searchNews().collectLatest {
                        newsAdapter.notifyDataSetChanged()
                        newsAdapter.submitData(it)
                        closeKeyboard()
                    }
                }
                return true
            }

        })
    }

//    private fun populateNews() {
//        binding.apply {
//            newsAdapter = ListNewsAdapter()
//
//            rvNews.setHasFixedSize(true)
//            rvNews.adapter = newsAdapter.withLoadStateHeaderAndFooter(
//                header = NewsLoadStateAdapter{ newsAdapter.retry() },
//                footer = NewsLoadStateAdapter { newsAdapter.retry() }
//            )
//            rvNews.layoutManager =  LinearLayoutManager(this@ListNewsActivity)
//        }
//        lifecycleScope.launch {
//            viewModel.testNews.collectLatest {
//                Log.d("HasilActivity",it.toString())
//                newsAdapter.submitData(it)
//            }
//            viewModel.getListNews().observe(this@ListNewsActivity, {
//                Log.d("DataNews",it.toString())
//                if (it != null) {
//                    newsAdapter.submitData(lifecycle, it)
//                }
//            })
//        }
//
//        newsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: NewsModel) {
//                val detailIntent = Intent(this@ListNewsActivity, NewsActivity::class.java)
//                detailIntent.putExtra(NewsActivity.EXTRA_NEWS, data.id)
//                startActivity(detailIntent)
//            }
//        })
//    }
    fun populateNews(){
        newsAdapter = ListNewsAdapter()
        binding.rvNews.apply {
//            adapter = newsAdapter

            adapter = newsAdapter.withLoadStateHeaderAndFooter(
                header = NewsLoadStateAdapter{newsAdapter.retry()},
                footer = NewsLoadStateAdapter{newsAdapter.retry()}
            )

            newsAdapter.addLoadStateListener { loadState ->

                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                isVisible = loadState.source.refresh is LoadState.NotLoading
                binding.btnRetry.isVisible = loadState.source.refresh is LoadState.Error

                // No results found
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    newsAdapter.itemCount < 1
                ) {
                    isVisible = false
                    binding.tvNoSearchResult.isVisible = true
                } else {
                    binding.tvNoSearchResult.isVisible = false
                }
            }
            layoutManager = LinearLayoutManager(this@ListNewsActivity)
            setHasFixedSize(true)
        }

        lifecycleScope.launch {
            viewModel.listNews.collectLatest {
                newsAdapter.submitData(it)
            }
        }


    }

    private fun closeKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}