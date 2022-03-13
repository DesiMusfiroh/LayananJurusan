package com.layanan.jurusan.ui.news

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.databinding.ActivityNewsBinding
import com.layanan.jurusan.databinding.ItemNewsBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var viewModel: DetailNewsViewModel

    companion object {
        const val EXTRA_NEWS = "news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailNewsViewModel::class.java]
        val extras = intent.extras
        Log.d("Extras",extras.toString())

        if (extras != null){
            val newsId = extras.getInt(EXTRA_NEWS,0)
            Log.d("NewsId",newsId.toString())
            viewModel.setSelectedNews(newsId)

            viewModel.getDetailNews().observe(this,{
                setUpView(it!!)
            })
        }




    }

    fun setUpView(news: NewsModel){
        binding.apply {
            shimmerImg.stopShimmer()
            shimmerImg.visibility = View.GONE
            progressBar.visibility = View.GONE
            llContent.visibility = View.VISIBLE
            tvTitle.text = news.title
            tvDate.text = news.published_at
            newsContent.text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                Html.fromHtml(news.desc,Html.FROM_HTML_MODE_COMPACT)
            }else{
                Html.fromHtml(news.desc)
            }
            tvUser.text = news.author

            Glide.with(this@NewsActivity)
                .load(news?.image)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imgBackdrop)
            imgBackdrop.visibility = View.VISIBLE

        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

}