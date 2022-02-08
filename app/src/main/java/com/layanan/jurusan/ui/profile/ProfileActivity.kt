package com.layanan.jurusan.ui.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.UserModel
import com.layanan.jurusan.databinding.ActivityProfileBinding
import com.layanan.jurusan.ui.login.LoginActivity
import com.layanan.jurusan.viewmodel.ViewModelFactory

class ProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: ActivityProfileBinding
    private lateinit var jwtToken: String
    private lateinit var userPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
        setContentView(binding.root)
        supportActionBar?.hide()

        userPref = this.getSharedPreferences("user", MODE_PRIVATE)

        jwtToken = userPref.getString("token","devicetoken").toString()

        viewModel.getUserProfile(jwtToken).observe(this,{
            Log.d("ProfileUser",it.toString())
            setUpView(it)
        })
    }

    private fun setUpView(user: UserModel){
        binding.apply {
            tvNama.text = user.mahasiswa.nama
            tvNim.text = user.mahasiswa.nim
            tvAngkatan.text = user.mahasiswa.angkatan
            tvProdi.text = user.mahasiswa.prodi?.nama
        }

        binding.apply {
            cvInfo.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    if (cardTableInfo.visibility == View.VISIBLE) {
                        TransitionManager.beginDelayedTransition(cvInfo, AutoTransition())
                        cardTableInfo.visibility = View.GONE
                        icArrowInfo.setImageResource(R.drawable.ic_arrow_next)
                    } else {
                        TransitionManager.beginDelayedTransition(cvInfo, AutoTransition())
                        cardTableInfo.visibility = View.VISIBLE
                        icArrowInfo.setImageResource(R.drawable.ic_arrow_to_bottom)
                    }
                }
            })
        }

        binding.cvSignature.setOnClickListener {
            val intent = Intent(this, SignatureActivity::class.java)
            startActivity(intent)
        }
        binding.cvLogout.setOnClickListener {

            viewModel.logout(jwtToken).observe(this,{
                if (it.message == "success"){
                    userPref.edit().clear().apply()
                    val intent = Intent(this,LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                }
            })
        }
    }

//    fun setUpAccordionView(){
//        cvSertifikat = binding.cvSertifikat
//        binding.tvSertifikat.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                TransitionManager.beginDelayedTransition(cvSertifikat)
//                if (clickArrow1 % 2 == 0) {
//                    binding.listSertifikat.animate()
//                        .alpha(1f)
//                        .setDuration(300)
//                        .setListener(object : AnimatorListenerAdapter() {
//                            override fun onAnimationEnd(animation: Animator?) {
//                                binding.listSertifikat.setVisibility(View.VISIBLE)
//                                binding.line.setVisibility(View.VISIBLE)
//                                super.onAnimationEnd(animation)
//                            }
//                        })
//                    binding.iconArrow.setImageResource(R.drawable.dropup_black)
//                } else {
//                    binding.listSertifikat.animate()
//                        .alpha(0f)
//                        .setDuration(300)
//                        .setListener(object : AnimatorListenerAdapter() {
//                            override fun onAnimationEnd(animation: Animator?) {
//                                binding.listSertifikat.setVisibility(View.GONE)
//                                binding.line.setVisibility(View.GONE)
//                                super.onAnimationEnd(animation)
//                            }
//                        })
//                    binding.iconArrow.setImageResource(R.drawable.dropdown_black)
//                }
//                clickArrow1++
//            }
//        })
//    }
}