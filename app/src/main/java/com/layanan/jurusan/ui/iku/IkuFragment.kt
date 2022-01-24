package com.layanan.jurusan.ui.iku

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.layanan.jurusan.databinding.FragmentIkuBinding

class AssociationFragment : Fragment() {
    private lateinit var binding: FragmentIkuBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentIkuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardIku1.setOnClickListener {
            val iku1Intent = Intent(context, Iku1Activity::class.java)
            startActivity(iku1Intent)
        }
        binding.cardIku2.setOnClickListener {
            val iku2Intent = Intent(context, Iku2Activity::class.java)
            startActivity(iku2Intent)
        }
    }
}