package com.layanan.jurusan.ui.civitas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.layanan.jurusan.R
import com.layanan.jurusan.databinding.ActivityDosenBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class DosenActivity : AppCompatActivity() {
     private lateinit var binding: ActivityDosenBinding
     private lateinit var viewModel: DosenViewModel
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

         listKegiatanTridharma()
     }

    private fun listKegiatanTridharma() {
        val arrayAdapter: ArrayAdapter<String>
        // dummy
        val users = arrayOf("Virat Kohli", "Rohit Sharma", "Steve Smith", "Kane Williamson", "Ross Taylor")
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        binding.listKegiatanTridharma.adapter = arrayAdapter

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

