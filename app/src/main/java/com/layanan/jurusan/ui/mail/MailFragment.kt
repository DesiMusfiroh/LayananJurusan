package com.layanan.jurusan.ui.mail

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.layanan.jurusan.databinding.FragmentMailBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory


class MailFragment : Fragment() {
    private lateinit var binding: FragmentMailBinding
    private lateinit var viewModel: MailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MailViewModel::class.java]

    }

    private fun showJenisSurat(tipeSurat: String?) {
        TODO("Not yet implemented")
    }
}


