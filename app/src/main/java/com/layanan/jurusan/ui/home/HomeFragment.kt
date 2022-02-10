package com.layanan.jurusan.ui.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.messaging.FirebaseMessaging
import com.layanan.jurusan.FcmServices
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.databinding.FragmentHomeBinding
import com.layanan.jurusan.ui.announcement.AnnouncementActivity
import com.layanan.jurusan.ui.announcement.ListAnnouncementActivity
import com.layanan.jurusan.ui.document.ListDocumentActivity
import com.layanan.jurusan.ui.jurusan.JurusanActivity
import com.layanan.jurusan.ui.login.LoginActivity
import com.layanan.jurusan.ui.news.ListNewsActivity
import com.layanan.jurusan.ui.news.NewsActivity
import com.layanan.jurusan.ui.notification.NotificationActivity
import com.layanan.jurusan.ui.prodi.ProdiActivity
import com.layanan.jurusan.ui.profile.ProfileActivity
import com.layanan.jurusan.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var newsAdapter: HomeNewsAdapter
    private lateinit var announcementAdapter: HomeAnnouncementAdapter
    private var userPref: SharedPreferences? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        userPref = context?.getSharedPreferences("user",
            AppCompatActivity.MODE_PRIVATE
        )
        binding.btnProfile.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.btnNotification.setOnClickListener {
            val intent = Intent(context, NotificationActivity::class.java)
            startActivity(intent)
        }

        binding.viewNews.setOnClickListener {
            val listNewsIntent = Intent(context, ListNewsActivity::class.java)
            startActivity(listNewsIntent)
        }

        binding.viewAnnouncement.setOnClickListener {
            val listAnnouncementIntent = Intent(context, ListAnnouncementActivity::class.java)
            startActivity(listAnnouncementIntent)
        }

        binding.menuNews.setOnClickListener {
            val listNewsIntent = Intent(context, ListNewsActivity::class.java)
            startActivity(listNewsIntent)
        }

        binding.menuAnnouncement.setOnClickListener {
            val listAnnouncementIntent = Intent(context, ListAnnouncementActivity::class.java)
            startActivity(listAnnouncementIntent)
        }

        binding.menuDocument.setOnClickListener {
            val listDocumentIntent = Intent(context, ListDocumentActivity::class.java)
            startActivity(listDocumentIntent)
        }

        binding.menuJurusan.setOnClickListener {
            val jurusanIntent = Intent(context, JurusanActivity::class.java)
            startActivity(jurusanIntent)
        }

        binding.cardSistemInformasi.setOnClickListener {
            val intent = Intent(context,ProdiActivity::class.java)
            intent.putExtra(ProdiActivity.EXTRA_PRODI_NAME,"sistem_informasi")
            startActivity(intent)
        }

        binding.cardTeknikElektro.setOnClickListener {
            val intent = Intent(context,ProdiActivity::class.java)
            intent.putExtra(ProdiActivity.EXTRA_PRODI_NAME,"teknik_elektro")
            startActivity(intent)
        }

        populateNews()
        populateAnnouncement()
        populateUserInfo()
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
                    override fun onItemClicked(data: NewsModel) {
                        Log.d("DataId",data.id.toString())
                        val intent = Intent(activity, NewsActivity::class.java)
                        intent.putExtra(NewsActivity.EXTRA_NEWS,data.id)
                        startActivity(intent)
                    }
                })
            }
        })
    }

    private fun populateUserInfo(){
        val jwtToken = userPref?.getString("token","devicetoken")
        viewModel.getUserProfile(jwtToken!!).observe(viewLifecycleOwner,{
            if (it == null){
                userPref?.edit()?.clear()?.apply()
                startActivity(Intent(context,LoginActivity::class.java))
            }
            binding.haiUser.text = "Hai, ${it?.mahasiswa?.nama}"
        })
    }

    fun populateAnnouncement(){
        viewModel.getLatestAnnouncement().observe(viewLifecycleOwner,{
            Log.d("Announcement",it.toString())
            if (it != null){
                announcementAdapter = HomeAnnouncementAdapter(it, requireContext())
                announcementAdapter.notifyDataSetChanged()

                binding.apply {
                    rvAnnouncement.layoutManager = LinearLayoutManager(context)
                    rvAnnouncement.setHasFixedSize(true)
                    rvAnnouncement.adapter = announcementAdapter
                }
                announcementAdapter.setOnItemClickCallback(object : HomeAnnouncementAdapter.OnItemClickCallback{
                    override fun onItemClicked(data: AnnouncementModel) {
                        val intent = Intent(activity, AnnouncementActivity::class.java)
                        val putExtra = intent.putExtra(AnnouncementActivity.EXTRA_ANNOUNCEMENT, data.id)
                        startActivity(intent)
                    }

                })
            }
        })
    }

}