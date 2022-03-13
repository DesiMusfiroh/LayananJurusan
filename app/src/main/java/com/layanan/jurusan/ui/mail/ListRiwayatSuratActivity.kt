package com.layanan.jurusan.ui.mail


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.RiwayatSuratModel
import com.layanan.jurusan.databinding.ActivityListRiwayatSuratBinding
import com.layanan.jurusan.ui.profile.SignatureViewModel
import com.layanan.jurusan.viewmodel.ViewModelFactory
import okhttp3.internal.notify

class ListRiwayatSuratActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListRiwayatSuratBinding
    private lateinit var viewModel: ListRiwayatSuratViewModel
    private lateinit var adapter: RiwayatSuratAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListRiwayatSuratBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ListRiwayatSuratViewModel::class.java]
        populateRiwayatSurat()
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    fun populateRiwayatSurat(){
        val userPref = this@ListRiwayatSuratActivity.getSharedPreferences("user",
            AppCompatActivity.MODE_PRIVATE
        )

        val jwtToken = userPref?.getString("token","devicetoken")
        viewModel.getRiwayatSurat(jwtToken!!).observe(this,{
            adapter = RiwayatSuratAdapter(it)
            adapter.notifyDataSetChanged()
            with(binding){
                rvRiwayatSurat.layoutManager = LinearLayoutManager(this@ListRiwayatSuratActivity)
                rvRiwayatSurat.setHasFixedSize(true)
                rvRiwayatSurat.adapter = adapter
            }
            adapter.setOnItemClickCallback(object : RiwayatSuratAdapter.OnItemClickCallback {
                override fun onItemClicked(data: RiwayatSuratModel) {
                    val intent = Intent(applicationContext,RiwayatSuratActivity::class.java)
                    intent.putExtra(RiwayatSuratActivity.EXTRA_MAIL, data.id)
                    startActivity(intent)
                }
            })
        })
    }
}