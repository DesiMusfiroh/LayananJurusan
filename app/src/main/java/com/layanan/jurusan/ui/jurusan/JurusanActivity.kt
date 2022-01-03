package com.layanan.jurusan.ui.jurusan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.ProfileJurusanModel
import com.layanan.jurusan.databinding.ActivityJurusanBinding
import com.layanan.jurusan.ui.home.HomeViewModel
import com.layanan.jurusan.viewmodel.ViewModelFactory

class JurusanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJurusanBinding
    private lateinit var viewModel: JurusanViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJurusanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[JurusanViewModel::class.java]

        viewModel.getProfileJurusan().observe(this,{
            setUpView(it)
        })

    }

    fun setUpView(data: ProfileJurusanModel){
        binding.apply {
            tvJurusan.text = data.name
            Glide.with(this@JurusanActivity)
                .load(data.image)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imgJurusan)
            tvDesc.text = data.desc
        }
    }


}