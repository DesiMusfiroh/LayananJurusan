package com.layanan.jurusan.ui.mail

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.RiwayatSuratModel
import com.layanan.jurusan.databinding.ActivityRiwayatSuratBinding
import com.layanan.jurusan.ui.news.NewsActivity
import com.layanan.jurusan.viewmodel.ViewModelFactory

class RiwayatSuratActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRiwayatSuratBinding
    private lateinit var viewModel: RiwayatSuratViewModel
    private lateinit var dataRiwayatSurat: RiwayatSuratModel
    companion object {
        const val EXTRA_MAIL = "mail"
    }
    private lateinit var userPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatSuratBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[RiwayatSuratViewModel::class.java]

        userPref = this.getSharedPreferences("user",
            AppCompatActivity.MODE_PRIVATE
        )
        populateView()

    }

    private fun populateView(){
        val extras = intent.extras
        val riwayatSuratId = extras?.getInt(EXTRA_MAIL,0)
        val jwtToken = userPref.getString("token","devicetoken")

        viewModel.setSelectedData(riwayatSuratId!!)
        viewModel.showRiwayatSurat(jwtToken!!).observe(this,{
            dataRiwayatSurat = it
            binding.tvTitle.text = dataRiwayatSurat.jenisSurat?.judul
            binding.tvStatus.text = dataRiwayatSurat.status

            if(dataRiwayatSurat.status == "telah diverifikasi"){
                binding.btnDownload.visibility = View.VISIBLE
                binding.btnDownload.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(dataRiwayatSurat.file)
                    startActivity(intent)
                }
            }else{
                binding.btnDownload.visibility = View.GONE
            }

            when(dataRiwayatSurat.status){
                "telah diverifikasi" -> binding.tvStatus.background = applicationContext.resources.getDrawable(R.color.secondary)
                "sedang diproses"-> binding.tvStatus.background = applicationContext.resources.getDrawable(R.color.process)
                else -> binding.tvStatus.background = applicationContext.resources.getDrawable(R.color.decline)
            }

        })

    }
}