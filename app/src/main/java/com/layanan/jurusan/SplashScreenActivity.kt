package com.layanan.jurusan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.layanan.jurusan.ui.login.LoginActivity
import com.layanan.jurusan.ui.student.DashboardStudentActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            val userPref = applicationContext.getSharedPreferences("user", MODE_PRIVATE)
            val isLoggedIn = userPref.getBoolean("isLoggedIn", false)
            if (isLoggedIn) {
                startActivity(Intent(this, DashboardStudentActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
        },800)
    }
}