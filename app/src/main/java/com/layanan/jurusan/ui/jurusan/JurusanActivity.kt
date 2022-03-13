package com.layanan.jurusan.ui.jurusan

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.ProfileJurusanModel
import com.layanan.jurusan.databinding.ActivityJurusanBinding
import com.layanan.jurusan.ui.himpunan.HimpunanActivity
import com.layanan.jurusan.ui.prodi.ProdiActivity
import com.layanan.jurusan.viewmodel.ViewModelFactory

@Suppress("DEPRECATION")
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

        binding.apply {
            btnBack.setOnClickListener { onBackPressed() }
            cardSi.setOnClickListener {
                val intent = Intent(this@JurusanActivity, ProdiActivity::class.java)
                intent.putExtra(ProdiActivity.EXTRA_PRODI_NAME,"sistem_informasi")
                startActivity(intent)
            }
            cardTe.setOnClickListener {
                val intent = Intent(this@JurusanActivity,ProdiActivity::class.java)
                intent.putExtra(ProdiActivity.EXTRA_PRODI_NAME,"teknik_elektro")
                startActivity(intent)
            }

        }
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

            tvDesc.text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                Html.fromHtml(data.desc, Html.FROM_HTML_MODE_COMPACT)
            } else Html.fromHtml(data.desc)

            tvVisi.text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                Html.fromHtml(data.vision, Html.FROM_HTML_MODE_COMPACT)
            } else Html.fromHtml(data.vision)

            tvMisi.text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                Html.fromHtml(data.mission, Html.FROM_HTML_MODE_COMPACT)
            } else Html.fromHtml(data.mission)
        }
    }
}