package com.layanan.jurusan.ui.iku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.layanan.jurusan.databinding.ActivityIku2Binding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class Iku2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIku2Binding
    private lateinit var viewModel: IkuViewModel
    private lateinit var adapter: Iku2Adapter
    companion object {
        const val EXTRA_YEAR = "year"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIku2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[IkuViewModel::class.java]

        val extras = intent.extras
        val year = extras?.getString(EXTRA_YEAR,"0")
        Log.d("ExtraTahun",year!!)
        populateIku2(year)
    }

    private fun populateIku2(year: String) {
        viewModel.getIku2(year).observe(this,{
            Log.d("Iku2Activity",it.toString())
            adapter = Iku2Adapter(it)
            adapter.notifyDataSetChanged()

            with(binding){
                rvIku2.layoutManager = LinearLayoutManager(this@Iku2Activity)
                rvIku2.setHasFixedSize(true)
                rvIku2.adapter = adapter
            }
        })
    }
}