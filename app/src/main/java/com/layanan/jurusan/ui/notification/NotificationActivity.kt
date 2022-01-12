package com.layanan.jurusan.ui.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.layanan.jurusan.R
import com.layanan.jurusan.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private lateinit var viewModel: NotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        supportActionBar?.hide()
    }
}