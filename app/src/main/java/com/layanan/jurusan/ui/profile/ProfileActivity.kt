package com.layanan.jurusan.ui.profile

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.card.MaterialCardView
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.UserModel
import com.layanan.jurusan.databinding.ActivityProfileBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class ProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: ActivityProfileBinding
    private lateinit var cvSertifikat: MaterialCardView
    private lateinit var cvPengalamanKerja: MaterialCardView
    private lateinit var cvKegiatan: MaterialCardView
    private lateinit var cvKaryaTulis: MaterialCardView

    private var clickArrow1 = 1
    private var clickArrow2 = 1
    private var clickArrow3 = 1
    private var clickArrow4 = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
        setContentView(binding.root)
        supportActionBar?.hide()
        setUpAccordionView()

        val userPref = this.getSharedPreferences("user",
            AppCompatActivity.MODE_PRIVATE
        )

        val jwtToken = userPref?.getString("token","devicetoken")
        if (jwtToken != null) {
            Log.d("Jwt",jwtToken)
        }
        viewModel.getUserProfile(jwtToken!!).observe(this,{
            setUpView(it)
        })
    }

    fun setUpView(user: UserModel){
        binding.apply {
            tvNim.text = user.nomor_induk
        }
    }

    fun setUpAccordionView(){
        cvSertifikat = binding.cvSertifikat
        cvPengalamanKerja = binding.cvPengalamanKerja
        cvKegiatan = binding.cvKegiatan
        cvKaryaTulis = binding.cvKaryaTulis

        binding.tvSertifikat.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                TransitionManager.beginDelayedTransition(cvSertifikat)
                if (clickArrow1 % 2 == 0) {
                    binding.listSertifikat.animate()
                        .alpha(1f)
                        .setDuration(300)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                binding.listSertifikat.setVisibility(View.VISIBLE)
                                binding.line.setVisibility(View.VISIBLE)
                                super.onAnimationEnd(animation)
                            }
                        })
                    binding.iconArrow.setImageResource(R.drawable.dropup_black)
                } else {
                    binding.listSertifikat.animate()
                        .alpha(0f)
                        .setDuration(300)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                binding.listSertifikat.setVisibility(View.GONE)
                                binding.line.setVisibility(View.GONE)
                                super.onAnimationEnd(animation)
                            }
                        })
                    binding.iconArrow.setImageResource(R.drawable.dropdown_black)
                }
                clickArrow1++
            }
        })


        binding.tvPengalamanKerja.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                TransitionManager.beginDelayedTransition(cvPengalamanKerja)
                if (clickArrow2 % 2 == 0) {
                    binding.listPengalamanKerja.animate()
                        .alpha(1f)
                        .setDuration(300)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                binding.listPengalamanKerja.setVisibility(View.VISIBLE)
                                binding.line2.setVisibility(View.VISIBLE)
                                super.onAnimationEnd(animation)
                            }
                        })
                    binding.iconArrow2.setImageResource(R.drawable.dropup_black)
                } else {
                    binding.listPengalamanKerja.animate()
                        .alpha(0f)
                        .setDuration(300)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                binding.listPengalamanKerja.setVisibility(View.GONE)
                                binding.line2.setVisibility(View.GONE)
                                super.onAnimationEnd(animation)
                            }
                        })
                    binding.iconArrow2.setImageResource(R.drawable.dropdown_black)
                }
                clickArrow2++
            }
        })

        binding.tvKegiatan.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                TransitionManager.beginDelayedTransition(cvKegiatan)
                if (clickArrow3 % 2 == 0) {
                    binding.listKegiatan.animate()
                        .alpha(1f)
                        .setDuration(300)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                binding.listKegiatan.setVisibility(View.VISIBLE)
                                binding.line3.setVisibility(View.VISIBLE)
                                super.onAnimationEnd(animation)
                            }
                        })
                    binding.iconArrow3.setImageResource(R.drawable.dropup_black)
                } else {
                    binding.listKegiatan.animate()
                        .alpha(0f)
                        .setDuration(300)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                binding.listKegiatan.setVisibility(View.GONE)
                                binding.line3.setVisibility(View.GONE)
                                super.onAnimationEnd(animation)
                            }
                        })
                    binding.iconArrow3.setImageResource(R.drawable.dropdown_black)
                }
                clickArrow3++
            }
        })

        binding.tvKaryaTulis.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                TransitionManager.beginDelayedTransition(cvKaryaTulis)
                if (clickArrow4 % 2 == 0) {
                    binding.listKaryaTulis.animate()
                        .alpha(1f)
                        .setDuration(300)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                binding.listKaryaTulis.setVisibility(View.VISIBLE)
                                binding.line4.setVisibility(View.VISIBLE)
                                super.onAnimationEnd(animation)
                            }
                        })
                    binding.iconArrow4.setImageResource(R.drawable.dropup_black)
                } else {
                    binding.listKaryaTulis.animate()
                        .alpha(0f)
                        .setDuration(300)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                binding.listKaryaTulis.setVisibility(View.GONE)
                                binding.line4.setVisibility(View.GONE)
                                super.onAnimationEnd(animation)
                            }
                        })
                    binding.iconArrow4.setImageResource(R.drawable.dropdown_black)
                }
                clickArrow4++
            }
        })
    }
}