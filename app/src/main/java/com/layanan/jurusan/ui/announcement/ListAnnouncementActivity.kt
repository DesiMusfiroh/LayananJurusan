package com.layanan.jurusan.ui.announcement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.layanan.jurusan.databinding.ActivityListAnnouncementBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListAnnouncementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListAnnouncementBinding
    private lateinit var announcementAdapter: ListAnnouncementAdapter
    private lateinit var viewModel: AnnouncementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[AnnouncementViewModel::class.java]
        populateAnnouncement()
    }

    private fun populateAnnouncement(){
        announcementAdapter = ListAnnouncementAdapter()
        binding.rvAnnouncement.apply {
            adapter = announcementAdapter
            layoutManager = LinearLayoutManager(this@ListAnnouncementActivity)
            setHasFixedSize(true)
        }

        lifecycleScope.launch {
            viewModel.listAnnouncement.collectLatest {
                Log.d("pengumuman activity", it.toString())
                announcementAdapter.submitData(it)
            }
        }
    }
}
