package com.layanan.jurusan.ui.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.layanan.jurusan.R
import com.layanan.jurusan.databinding.ActivityDashboardStudentBinding
import com.layanan.jurusan.ui.civitas.CivitasFragment
import com.layanan.jurusan.ui.iku.AssociationFragment
import com.layanan.jurusan.ui.home.HomeFragment
import com.layanan.jurusan.ui.mail.MailFragment

class DashboardStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.navView.setItemSelected(R.id.navigation_home, true)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, HomeFragment())
            .commit()

        bottomMenu()
    }

    private fun bottomMenu() {
        binding.navView.setOnItemSelectedListener {
            val fragment: Fragment = when (it) {
                R.id.navigation_civitas ->  CivitasFragment()
                R.id.navigation_iku ->  AssociationFragment()
                R.id.navigation_mail -> MailFragment()
                else -> HomeFragment()
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }
    }

}