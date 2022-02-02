package com.layanan.jurusan.ui.iku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.Iku7Model
import com.layanan.jurusan.data.model.Iku8Model
import com.layanan.jurusan.databinding.ActivityIku7Binding
import com.layanan.jurusan.databinding.ActivityIku8Binding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class Iku8Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIku8Binding
    private lateinit var viewModel: IkuViewModel
    private lateinit var adapter: Iku8Adapter
    private lateinit var dataResponse: List<Iku8Model>
    companion object {
        const val EXTRA_YEAR = "2022"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIku8Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[IkuViewModel::class.java]

        val extras = intent.extras
        val year = extras?.getString(EXTRA_YEAR,"0")
        Log.d("ExtraTahun",year!!)
        populateIku8(year)
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun populateIku8(year: String) {
        viewModel.getIku8(year).observe(this,{
            dataResponse = it
            adapter = Iku8Adapter(dataResponse)
            adapter.notifyDataSetChanged()
            binding.apply {
                rvIku8.layoutManager = LinearLayoutManager(this@Iku8Activity)
                rvIku8.setHasFixedSize(true)
                rvIku8.adapter = adapter
            }
        })
    }
}