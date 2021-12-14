package com.layanan.jurusan.ui.announcement

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.lifecycle.ViewModelProvider
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

    fun setUpView(data: AnnouncementModel){
        binding.apply {
            tvTitle.text = data.title
            tvAnnouncementContent.text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                Html.fromHtml(data.desc, Html.FROM_HTML_MODE_COMPACT)
            }else{
                Html.fromHtml(data.desc)
            }

            tvCategory.text = data.category
        }
    }
}