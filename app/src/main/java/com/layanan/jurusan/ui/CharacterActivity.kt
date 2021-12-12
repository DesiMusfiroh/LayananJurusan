package com.layanan.jurusan.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.layanan.jurusan.R
import com.layanan.jurusan.databinding.ActivityCharacterBinding
import com.layanan.jurusan.ui.news.DetailNewsViewModel
import com.layanan.jurusan.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding
    private lateinit var viewModel: CharacterViewModel
    private lateinit var characterAdapter: CharacterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[CharacterViewModel::class.java]

        setupRecyclerView()
        loadData()
    }

    private fun setupRecyclerView() {

        characterAdapter = CharacterAdapter()

        binding.recyclerView.apply {
            adapter = characterAdapter
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
        }
    }

    private fun loadData() {

//        lifecycleScope.launch {
//            viewModel.listData.collectLatest {
//
//                Log.d("aaa", "load: $it")
//                characterAdapter.submitData(it)
//            }
//
//        }
    }


}