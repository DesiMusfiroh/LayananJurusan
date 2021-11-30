package com.layanan.jurusan.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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

        init()
    }

    fun init(){
        binding.btnLogin.setOnClickListener {
            if (validate()){
                val username = binding.etUsername.text.toString()
                val password = binding.etPassword.text.toString()

                Log.d("LoginActivity",username)
                Log.d("LoginActivity",password)

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
                        startActivity(Intent(this,DashboardStudentActivity::class.java))
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




}