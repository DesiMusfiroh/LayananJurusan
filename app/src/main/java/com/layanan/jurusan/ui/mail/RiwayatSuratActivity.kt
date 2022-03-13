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
import java.text.SimpleDateFormat

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
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[RiwayatSuratViewModel::class.java]

        userPref = this.getSharedPreferences("user",
            AppCompatActivity.MODE_PRIVATE
        )
        populateView()
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun populateView(){
        val extras = intent.extras
        val riwayatSuratId = extras?.getInt(EXTRA_MAIL,0)
        val jwtToken = userPref.getString("token","devicetoken")

        viewModel.setSelectedData(riwayatSuratId!!)
        viewModel.showRiwayatSurat(jwtToken!!).observe(this,{
            dataRiwayatSurat = it
            Log.d("RiwayatSurat",it.toString())
            binding.tvTitle.text = dataRiwayatSurat.jenisSurat?.judul
            binding.tvStatus.text = dataRiwayatSurat.status
            binding.tvNama.text = dataRiwayatSurat.user?.mahasiswa?.nama
//            binding.tvTanggalPengajuan.text = dataRiwayatSurat.tanggalPengajuan

            val arrDateTime = it.tanggalPengajuan?.split("\\.")
            var dateTime = arrDateTime?.get(0)

            dateTime = dateTime?.replace("T"," ")

            binding.tvTanggalPengajuan.text = dateTime?.let { it1 -> modifyDateFormat(it1) }

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



            binding.tvStatus.visibility = View.VISIBLE
            when(dataRiwayatSurat.status){
                "telah diverifikasi" -> binding.tvStatus.background = applicationContext.resources.getDrawable(R.color.secondary)
                "sedang diproses"-> binding.tvStatus.background = applicationContext.resources.getDrawable(R.color.process)
                else -> binding.tvStatus.background = applicationContext.resources.getDrawable(R.color.decline)
            }

        })

    }

    fun modifyDateFormat(inputDate: String): String? {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inputDate)
        return SimpleDateFormat("dd MMMM yyyy, HH:mm").format(date)
    }
}