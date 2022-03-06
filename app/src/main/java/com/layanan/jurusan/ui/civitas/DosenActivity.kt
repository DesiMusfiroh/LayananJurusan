package com.layanan.jurusan.ui.civitas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.*
import com.layanan.jurusan.databinding.ActivityDosenBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class DosenActivity : AppCompatActivity() {
     private lateinit var binding: ActivityDosenBinding
     private lateinit var viewModel: DosenViewModel
     private  var listKegiatanTridharma: ArrayList<KegiatanTriDharmaModel> = ArrayList()
     private var listPraktisi: ArrayList<PraktisiModel> = ArrayList()
     private var listPrestasiMembimbing : ArrayList<PrestasiMembimbingMahasiswa> = ArrayList()
     private var listSertifikatKompetensi : ArrayList<SertifikatKompetensi> = ArrayList()
     private var listHasilKerjaDosen : ArrayList<HasilKerjaDosen> = ArrayList()
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

         visibilityView(false)
         val extras = intent.extras
         if(extras != null){
             val id = extras.getInt(EXTRA_DOSEN,0)
             viewModel.getDetailDosen(id).observe(this,{
                 setupView(it)
             })
         }

     }

    private fun setupView(data: DosenModel) {
        binding.shimmerTvName.stopShimmer()
        binding.shimmerTvNoInduk.stopShimmer()
        binding.tvName.text = data.nama
        binding.tvNoInduk.text = data.noInduk
        binding.tvProdi.text = data.prodi?.nama

        binding.tvName.visibility = View.VISIBLE
        binding.tvNoInduk.visibility = View.VISIBLE
        binding.shimmerTvNoInduk.visibility = View.GONE
        binding.shimmerTvName.visibility = View.GONE

        addToArrayList(data)


        val kegiatanTriDharmaAdapter = KegiatanTriDharmaAdapter(this, listKegiatanTridharma)
        val praktisiAdapter =  PraktisiAdapter(this, listPraktisi)
        val prestasiMembimbingMahasiswaAdapter = PrestasiMembimbingMahasiswaAdapter(this,listPrestasiMembimbing)
        val sertifikatKompetensiAdapter = SertifikatKompetensiAdapter(this,listSertifikatKompetensi)
        val hasilKerjaDosenAdapter = HasilKerjaDosenAdapter(this,listHasilKerjaDosen)
        binding.listKegiatanTridharma.adapter = kegiatanTriDharmaAdapter
        binding.listPraktisi.adapter = praktisiAdapter
        binding.listPrestasiMembimbing.adapter = prestasiMembimbingMahasiswaAdapter
        binding.listSertifikatKompetensi.adapter = sertifikatKompetensiAdapter
        binding.listHasilKerjaDosen.adapter = hasilKerjaDosenAdapter

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
            cardPraktisi.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    if (cardListPraktisi.visibility == View.VISIBLE) {
                        TransitionManager.beginDelayedTransition(cardPraktisi, AutoTransition())
                        cardListPraktisi.visibility = View.GONE
                        iconArrowPraktisi.setImageResource(R.drawable.ic_arrow_next)
                    } else {
                        TransitionManager.beginDelayedTransition(cardPraktisi, AutoTransition())
                        cardListPraktisi.visibility = View.VISIBLE
                        iconArrowPraktisi.setImageResource(R.drawable.ic_arrow_to_bottom)
                    }
                }
            })
            cardPrestasiMembimbing.setOnClickListener(object: View.OnClickListener{
                override fun onClick(p0: View?) {
                    if(cardListPrestasiMembimbing.visibility == View.VISIBLE){
                        TransitionManager.beginDelayedTransition(cardPrestasiMembimbing, AutoTransition())
                        cardListPrestasiMembimbing.visibility = View.GONE
                        iconArrowPrestasiMembimbing.setImageResource(R.drawable.ic_arrow_next)
                    }else{
                        TransitionManager.beginDelayedTransition(cardPrestasiMembimbing, AutoTransition())
                        cardListPrestasiMembimbing.visibility = View.VISIBLE
                        iconArrowPrestasiMembimbing.setImageResource(R.drawable.ic_arrow_to_bottom)
                    }
                }

            })
            cardSertifikatKompetensi.setOnClickListener(object: View.OnClickListener{
                override fun onClick(p0: View?) {
                    if(cardListSertifikatKompetensi.visibility == View.VISIBLE){
                        TransitionManager.beginDelayedTransition(cardSertifikatKompetensi, AutoTransition())
                        cardListSertifikatKompetensi.visibility = View.GONE
                        iconArrowSertifikatKompetensi.setImageResource(R.drawable.ic_arrow_next)
                    }else{
                        TransitionManager.beginDelayedTransition(cardSertifikatKompetensi, AutoTransition())
                        cardListSertifikatKompetensi.visibility = View.VISIBLE
                        iconArrowSertifikatKompetensi.setImageResource(R.drawable.ic_arrow_to_bottom)
                    }
                }

            })
            cardHasilKerjaDosen.setOnClickListener(object: View.OnClickListener{
                override fun onClick(p0: View?) {
                    if(cardListHasilKerjaDosen.visibility == View.VISIBLE){
                        TransitionManager.beginDelayedTransition(cardHasilKerjaDosen, AutoTransition())
                        cardListHasilKerjaDosen.visibility = View.GONE
                        iconArrowHasilKerjaDosen.setImageResource(R.drawable.ic_arrow_next)
                    }else{
                        TransitionManager.beginDelayedTransition(cardHasilKerjaDosen, AutoTransition())
                        cardListHasilKerjaDosen.visibility = View.VISIBLE
                        iconArrowHasilKerjaDosen.setImageResource(R.drawable.ic_arrow_to_bottom)
                    }
                }

            })
        }

        visibilityView(true)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    fun visibilityView(isVisible: Boolean){
        with(binding){
            if(isVisible){
                progressBar.visibility = View.GONE
                cvAdditionalInfo.visibility = View.VISIBLE
                tableLayoutDosen.visibility = View.VISIBLE
            }else{
                progressBar.visibility = View.VISIBLE
                cvAdditionalInfo.visibility = View.GONE
                tableLayoutDosen.visibility = View.GONE
            }

        }
    }

    fun addToArrayList(data: DosenModel){
        for (item in data.kegiatanTriDharma!!){
            listKegiatanTridharma.add(item)
        }

        for(item in data.praktisi!!){
            listPraktisi.add(item)
        }

        for(item in data.prestasiMembimbingMahasiswa!!){
            listPrestasiMembimbing.add(item)
        }

        for(item in data.sertifikatKompetensi!!){
            listSertifikatKompetensi.add(item)
        }

        for(item in data.hasilKerjaDosen!!){
            listHasilKerjaDosen.add(item)
        }
    }

}

