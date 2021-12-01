package com.layanan.jurusan.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.databinding.FragmentHomeBinding
import com.layanan.jurusan.ui.news.ListNewsActivity
import com.layanan.jurusan.ui.profile.ProfileActivity
import com.layanan.jurusan.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var newsAdapter: HomeNewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        binding.btnProfile.setOnClickListener {
            val intent = Intent(context,ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.btnListNews.setOnClickListener {
            val listNewsIntent = Intent(context, ListNewsActivity::class.java)
            startActivity(listNewsIntent)
        }
        populateNews()
    }

    private fun populateNews() {
        viewModel.getLatestNews().observe(viewLifecycleOwner, { list ->
            if (list !== null) {
                newsAdapter = HomeNewsAdapter(list, requireContext())
                newsAdapter.notifyDataSetChanged()

                binding.apply {
                    rvNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    rvNews.setHasFixedSize(true)
                    rvNews.adapter = newsAdapter
                }
                newsAdapter.setOnItemClickCallback(object : HomeNewsAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: NewsModel) { }
                })
            }
        })
    }

}