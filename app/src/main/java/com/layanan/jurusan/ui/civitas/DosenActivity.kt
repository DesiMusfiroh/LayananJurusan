package com.layanan.jurusan.ui.civitas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.DosenModel
import com.layanan.jurusan.data.model.KegiatanTriDharmaModel
import com.layanan.jurusan.data.model.PraktisiModel
import com.layanan.jurusan.databinding.ActivityDosenBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class DosenActivity : AppCompatActivity() {
     private lateinit var binding: ActivityDosenBinding
     private lateinit var viewModel: DosenViewModel
     private  var listKegiatanTridharma: ArrayList<KegiatanTriDharmaModel> = ArrayList()
     private lateinit var listPraktisi: ArrayList<PraktisiModel>
     companion object {
         const val EXTRA_DOSEN = "dosen"
     }

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityDosenBinding.inflate(layoutInflater)
         setContentView(binding.root)
         supportActionBar?.hide()

         val factory = ViewModelFactory.getInstance(this)
         viewModel = ViewModelProvider(this, factory)[DosenViewModel::class.java]

         val extras = intent.extras
         if(extras != null){
             val id = extras.getInt(EXTRA_DOSEN,0)
             viewModel.getDetailDosen(id).observe(this,{
                 setupView(it)
             })
         }

     }

    private fun setupView(data: DosenModel) {
        binding.tvName.text = data.nama
        binding.tvNoInduk.text = data.noInduk
        binding.tvProdi.text = data.prodi?.nama

        for (item in data.kegiatanTriDharma!!){
            listKegiatanTridharma.add(item)
        }
        val kegiatanTriDharmaAdapter = KegiatanTriDharmaAdapter(this, listKegiatanTridharma)
        binding.listKegiatanTridharma.adapter = kegiatanTriDharmaAdapter

        binding.apply {
            cardKegiatanTridharma.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    if (cardListKegiatanTridharma.visibility == View.VISIBLE) {
                        TransitionManager.beginDelayedTransition(cardKegiatanTridharma, AutoTransition())
                        cardListKegiatanTridharma.visibility = View.GONE
                        iconArrowKegiatanTridharma.setImageResource(R.drawable.ic_arrow_next)
                    } else {
                        TransitionManager.beginDelayedTransition(cardKegiatanTridharma, AutoTransition())
                        cardListKegiatanTridharma.visibility = View.VISIBLE
                        iconArrowKegiatanTridharma.setImageResource(R.drawable.ic_arrow_to_bottom)
                    }
                }
            })
        }

    }
}

