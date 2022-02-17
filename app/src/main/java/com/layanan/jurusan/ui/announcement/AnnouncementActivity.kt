package com.layanan.jurusan.ui.announcement

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.databinding.ActivityAnnouncementBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class AnnouncementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnnouncementBinding
    private lateinit var viewModel: AnnouncementViewModel

    companion object {
        const val EXTRA_ANNOUNCEMENT = "news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[AnnouncementViewModel::class.java]

        val extras = intent.extras

        if (extras != null){
            val announcementId = extras.getInt(AnnouncementActivity.EXTRA_ANNOUNCEMENT,0)
            viewModel.setSelectedAnnouncement(announcementId)
            viewModel.getAnnouncement().observe(this,{
                setUpView(it!!)
            })
        }
    }

    private fun setUpView(data: AnnouncementModel){
        binding.apply {

            tvTitle.text = data.title
            tvDate.text = data.expires
            Glide.with(this@AnnouncementActivity)
                .load(data.image)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imgUser)
            tvAnnouncementContent.text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                Html.fromHtml(data.desc, Html.FROM_HTML_MODE_COMPACT)
            }else{
                Html.fromHtml(data.desc)
            }

            if (data.file != null){
                frameLayoutDownload.visibility = View.VISIBLE
                tvDownload.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(data.file)
                    startActivity(intent)
                }
            }else{
                frameLayoutDownload.visibility = View.GONE
            }
        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}