package com.layanan.jurusan.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.messaging.FirebaseMessaging
import com.layanan.jurusan.FcmServices
import com.layanan.jurusan.R
import com.layanan.jurusan.databinding.ActivityLoginBinding
import com.layanan.jurusan.ui.profile.ProfileViewModel
import com.layanan.jurusan.ui.student.DashboardStudentActivity
import com.layanan.jurusan.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
        //komen
        init()
    }

    fun init(){
        binding.btnLogin.setOnClickListener {
            if (validate()){
                val username = binding.etUsername.text.toString()
                val password = binding.etPassword.text.toString()

                viewModel.login(username,password).observe(this,{
                    if(it != null){
                        Log.d("Hasil",it.toString())
                        val userPreference = applicationContext.getSharedPreferences("user",0)
                        val editor = userPreference?.edit()
                        editor?.putInt("id",it.user.id)
                        editor?.putString("token",it.token)
                        editor?.putBoolean("isLoggedIn",true)
                        editor?.putString("username",it.user.username)
                        editor?.putString("nomor_induk",it.user.nomor_induk)
                        editor?.putInt("role",it.user.role)
                        editor?.apply()
//                        val fcmToken = fcmService()
//                        viewModel.saveFcmToken(fcmToken,it.token)

                        startActivity(Intent(this,DashboardStudentActivity::class.java))
                        finish()
                    }else {
                        Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private fun validate(): Boolean{
        if (binding.etUsername.text.toString().isEmpty()){
            binding.tLayoutUsername.isErrorEnabled = true
            binding.tLayoutUsername.error = "Username tidak boleh kosong"
            return false
        }
        if (binding.etPassword.text.toString().isEmpty()){
            binding.tLayoutPassword.isErrorEnabled = true
            binding.tLayoutPassword.error = "Password tidak boleh kosong"
            return false
        }
        return true
    }

    fun fcmService(): String{
        FirebaseMessaging.getInstance().subscribeToTopic("news")
        val msgs = getString(R.string.msg_subscribed)
        val deviceToken = FcmServices
        var token: String = ""
        val msg = getString(R.string.msg_token_fmt, deviceToken)
        FirebaseMessaging.getInstance().token.addOnSuccessListener { deviceToken ->
            val msg = getString(R.string.msg_token_fmt, deviceToken)
            Log.d("OKE",msg)

            Log.d("DeviceTokenLogin",deviceToken)
            token = deviceToken
        }

        return token

    }




}