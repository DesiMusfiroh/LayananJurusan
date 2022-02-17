package com.layanan.jurusan.ui.notification

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.layanan.jurusan.R
import com.layanan.jurusan.databinding.ActivityNotificationBinding
import com.layanan.jurusan.ui.profile.ProfileViewModel
import com.layanan.jurusan.viewmodel.ViewModelFactory

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private lateinit var viewModel: NotificationViewModel
    private lateinit var adapter: NotificationAdapter
    private lateinit var userPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[NotificationViewModel::class.java]
        supportActionBar?.hide()
        userPref = this.getSharedPreferences("user",
            AppCompatActivity.MODE_PRIVATE
        )

        populateView()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun populateView(){
        val jwtToken = userPref.getString("token","devicetoken")
        viewModel.getNotifikasi(jwtToken!!).observe(this,{
            adapter = NotificationAdapter(it)
            adapter.notifyDataSetChanged()
            with(binding){
                rvItem.layoutManager = LinearLayoutManager(this@NotificationActivity)
                rvItem.setHasFixedSize(true)
                rvItem.adapter = adapter
            }
        })

        viewModel.getCountNotifikasi(jwtToken).observe(this,{
            val editor = userPref.edit()
            editor.putInt("numNotif",it.data)
            editor.apply()
        })

    }
}