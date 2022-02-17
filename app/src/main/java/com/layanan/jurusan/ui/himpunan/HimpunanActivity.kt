package com.layanan.jurusan.ui.himpunan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.ProfileHimpunanModel
import com.layanan.jurusan.databinding.ActivityHimpunanBinding
import com.layanan.jurusan.ui.himpunan.HimpunanActivity
import com.layanan.jurusan.ui.himpunan.HimpunanViewModel
import com.layanan.jurusan.viewmodel.ViewModelFactory

class HimpunanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHimpunanBinding
    private lateinit var viewModel: HimpunanViewModel
    companion object{
        const val EXTRA_HIMPUNAN_NAME = "himasi"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHimpunanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[HimpunanViewModel::class.java]

        val extras = intent.extras
        Log.d("Extras",extras.toString())

//        if (extras != null){
//            val HimpunanName = extras.getString(HimpunanActivity.EXTRA_HIMPUNAN_NAME,"himasi")
//            viewModel.setSelectedHimpunan(HimpunanName)
//            viewModel.getProfileHimpunan().observe(this, {
//                setUpView(it)
//            })
//        }
//
//        binding.btnBack.setOnClickListener {
//            onBackPressed()
//        }
    }

}