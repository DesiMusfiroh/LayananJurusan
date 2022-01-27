package com.layanan.jurusan.ui.iku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.Iku6Model
import com.layanan.jurusan.data.model.Iku7Model
import com.layanan.jurusan.databinding.ActivityIku6Binding
import com.layanan.jurusan.databinding.ActivityIku7Binding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class Iku7Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIku7Binding
    private lateinit var viewModel: IkuViewModel
    private lateinit var adapter: Iku7Adapter
    private lateinit var dataResponse: List<Iku7Model>
    companion object {
        const val EXTRA_YEAR = "2022"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIku7Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[IkuViewModel::class.java]

        val extras = intent.extras
        val year = extras?.getString(EXTRA_YEAR,"0")
        Log.d("ExtraTahun",year!!)
        populateIku7(year)
    }

    private fun populateIku7(year: String){
        viewModel.getIku7(year).observe(this,{
            dataResponse = it
            adapter = Iku7Adapter(dataResponse)
            adapter.notifyDataSetChanged()
            binding.apply {
                rvIku7.layoutManager = LinearLayoutManager(this@Iku7Activity)
                rvIku7.setHasFixedSize(true)
                rvIku7.adapter = adapter
            }
        })
    }
}