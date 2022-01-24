package com.layanan.jurusan.ui.iku

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.layanan.jurusan.databinding.ActivityIku1Binding
import com.layanan.jurusan.viewmodel.ViewModelFactory


class Iku1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIku1Binding
    private lateinit var viewModel: Iku1ViewModel
    private lateinit var adapter: Iku1Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIku1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[Iku1ViewModel::class.java]

        populateIku1()
    }

    private fun populateIku1(){
        viewModel.getIku1("2022").observe(this,{
            Log.d("Iku1Activity",it.toString())
            adapter = Iku1Adapter(it)
            adapter.notifyDataSetChanged()

            with(binding){
                rvIku1.layoutManager = LinearLayoutManager(this@Iku1Activity)
                rvIku1.setHasFixedSize(true)
                rvIku1.adapter = adapter
            }
        })


    }


}