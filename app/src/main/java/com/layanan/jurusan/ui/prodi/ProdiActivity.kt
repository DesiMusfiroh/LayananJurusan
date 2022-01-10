package com.layanan.jurusan.ui.prodi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.ProfileJurusanModel
import com.layanan.jurusan.data.model.ProfileProdiModel
import com.layanan.jurusan.databinding.ActivityProdiBinding
import com.layanan.jurusan.ui.news.DetailNewsViewModel
import com.layanan.jurusan.ui.news.NewsActivity
import com.layanan.jurusan.viewmodel.ViewModelFactory

class ProdiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProdiBinding
    private lateinit var viewModel: ProdiViewModel
    companion object{
        const val EXTRA_PRODI_NAME = "sistem_informasi"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ProdiViewModel::class.java]

        val extras = intent.extras
        Log.d("Extras",extras.toString())

        if (extras != null){
            val prodiName = extras.getString(ProdiActivity.EXTRA_PRODI_NAME,"sistem_informasi")
            viewModel.setSelectedProdi(prodiName)
            viewModel.getProfileProdi().observe(this,{
                setUpView(it)
            })
        }
    }

    fun setUpView(data: ProfileProdiModel){
        binding.apply {
            tvProdi.text = data.name
            Glide.with(this@ProdiActivity)
                .load(data.logo)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imgProdi)
            tvDesc.text = data.desc
        }
    }
}