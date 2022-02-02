package com.layanan.jurusan.ui.iku

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.Iku5Model
import com.layanan.jurusan.data.model.Iku6Model
import com.layanan.jurusan.databinding.ActivityIku5Binding
import com.layanan.jurusan.databinding.ActivityIku6Binding
import com.layanan.jurusan.databinding.DialogIku5Binding
import com.layanan.jurusan.databinding.DialogIku6Binding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class Iku6Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIku6Binding
    private lateinit var viewModel: IkuViewModel
    private lateinit var adapter: Iku6Adapter
    private lateinit var dataResponse: List<Iku6Model>
    companion object {
        const val EXTRA_YEAR = "2022"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIku6Binding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[IkuViewModel::class.java]

        val extras = intent.extras
        val year = extras?.getString(EXTRA_YEAR,"0")
        Log.d("ExtraTahun",year!!)
        populateIku6(year)
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun populateIku6(year: String) {
        viewModel.getIku6(year).observe(this,{
            dataResponse = it
            adapter = Iku6Adapter(it)
            adapter.notifyDataSetChanged()
            binding.apply {
                rvIku6.layoutManager = LinearLayoutManager(this@Iku6Activity)
                rvIku6.setHasFixedSize(true)
                rvIku6.adapter = adapter
            }
            adapter.setOnItemClickCallback(object : Iku6Adapter.OnItemClickCallback{
                override fun onItemClicked(data: Iku6Model) {
                    showDialogData(data)
                }

            })
        })
    }

    private fun showDialogData(data: Iku6Model){
        val dialog = BottomSheetDialog(this)
        val dialogIku6Binding = DialogIku6Binding.inflate(layoutInflater)

        dialogIku6Binding.apply {
            tvNamaMitra.text = data.mitra?.nama
            tvJenisKegiatan.text = data.jenisKegiatan
            tvJenjang.text = data.jenjangProdi
            tvProdi.text = data.namaProdi
            tvNamaKegiatan.text = data.namaKegiatan
            tvTipeMitra.text = data.mitra?.tipeMitra
            tvNoPks.text = data.noPks
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogIku6Binding.root)
        dialog.show()
    }
}