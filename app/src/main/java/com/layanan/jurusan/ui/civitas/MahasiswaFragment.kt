package com.layanan.jurusan.ui.civitas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.layanan.jurusan.data.model.Mahasiswa
import com.layanan.jurusan.databinding.DialogFilterMahasiswaBinding
import com.layanan.jurusan.databinding.FragmentMahasiswaBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class MahasiswaFragment : Fragment() {
    private lateinit var binding: FragmentMahasiswaBinding
    private lateinit var viewModel: CivitasViewModel
    private var angkatanAdapter: ArrayAdapter<String>? = null
    private lateinit var angkatanSelected: String
    private lateinit var adapter: MahasiswaAdapter
    private lateinit var dataResponse: List<Mahasiswa>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMahasiswaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[CivitasViewModel::class.java]

        populateMahasiswa("Semua", "Semua", "aktif")
        binding.btnFilter.setOnClickListener {
            showFilterDialog()
        }
    }

    private fun populateMahasiswa(prodi: String, angkatan: String, status: String) {
        viewModel.getMahasiswa(prodi, angkatan, status).observe(viewLifecycleOwner, {
            if (it !== null) {
                dataResponse = it
                adapter = MahasiswaAdapter(dataResponse)
                adapter.notifyDataSetChanged()
                binding.apply {
                    rvMahasiswa.layoutManager = LinearLayoutManager(context)
                    rvMahasiswa.setHasFixedSize(true)
                    rvMahasiswa.adapter = adapter
                }
            }
        })
    }

    private fun showFilterDialog() {
        val dialog = BottomSheetDialog(requireContext())
        val dialogBinding = DialogFilterMahasiswaBinding.inflate(layoutInflater)

        viewModel.getAngkatan().observe(viewLifecycleOwner, { listAngkatan ->
            angkatanAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, listAngkatan) }
            dialogBinding.autoCompleteTextView.setAdapter(angkatanAdapter)
        })

        dialogBinding.btnFilter.setOnClickListener{

        }

        dialog.setContentView(binding.root)
        dialog.show()
    }
}