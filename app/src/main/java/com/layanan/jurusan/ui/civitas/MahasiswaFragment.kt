package com.layanan.jurusan.ui.civitas

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.layanan.jurusan.R
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
    var prodiCheckedId: Int = R.id.prodi_semua
    var statusCheckedId: Int = R.id.status_semua
    var prodiStr = "Semua"
    var statusStr = "Semua"
    var angkatanStr = "Semua"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMahasiswaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[CivitasViewModel::class.java]

        populateMahasiswa(prodiStr, angkatanStr, statusStr,isSearch = false)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searhMahasiswa.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        binding.searhMahasiswa.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.setSearchQuery(query,"mahasiswa")
                    populateMahasiswa(prodiStr,angkatanStr,statusStr,true)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.setSearchQuery(newText,"mahasiswa")
                    populateMahasiswa(prodiStr,angkatanStr,statusStr,true)
                }
                return true
            }

        })
        binding.btnFilter.setOnClickListener {
            showFilterDialog()
        }
    }

    private fun populateMahasiswa(prodi: String, angkatan: String, status: String, isSearch: Boolean?) {
        if (isSearch == false){
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
        }else{
            viewModel.getSearchMahasiswa(prodi, angkatan, status).observe(viewLifecycleOwner, {
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

    }

    private fun showFilterDialog() {
        Log.d("ProdiRbID",prodiCheckedId.toString())
        val dialog = BottomSheetDialog(requireContext())
        val dialogBinding = DialogFilterMahasiswaBinding.inflate(layoutInflater)
        val rgProdi = dialogBinding.radioGroupProdi
        val rgStatus = dialogBinding.radioGroupStatusMahasiswa
        var prodiRbChecked = rgProdi.findViewById<RadioButton>(prodiCheckedId)

        val statusRbChecked = rgStatus.findViewById<RadioButton>(statusCheckedId)

        prodiRbChecked!!.performClick()
        statusRbChecked!!.performClick()


        viewModel.getAngkatan().observe(viewLifecycleOwner, { listAngkatan ->
            angkatanAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, listAngkatan) }
            dialogBinding.spinnerAngkatan.adapter = angkatanAdapter
            dialogBinding.spinnerAngkatan.post {
                val angkatanSelectedIndex = listAngkatan.indexOf(angkatanStr)
                dialogBinding.spinnerAngkatan.setSelection(angkatanSelectedIndex) //langsung select ke selected index
            }
            dialogBinding.spinnerAngkatan.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    angkatanStr = dialogBinding.spinnerAngkatan.selectedItem.toString()
                    Log.d("StrAngkatan",angkatanStr)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        })

        dialogBinding.radioGroupProdi.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                prodiCheckedId = dialogBinding.radioGroupProdi.checkedRadioButtonId
                when(prodiCheckedId){
                    R.id.prodi_semua -> prodiStr = resources.getString(R.string.semua).trim()
                    R.id.prodi_si -> prodiStr = resources.getString(R.string.sistem_informasi).trim()
                    R.id.prodi_te -> prodiStr = resources.getString(R.string.teknik_elekto).trim()
                }
            }

        })
        dialogBinding.radioGroupStatusMahasiswa.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                statusCheckedId = dialogBinding.radioGroupStatusMahasiswa.checkedRadioButtonId
                when(statusCheckedId){
                    R.id.status_semua -> statusStr = resources.getString(R.string.semua).trim()
                    R.id.status_aktif -> statusStr = resources.getString(R.string.aktif).trim()
                    R.id.status_tidak_aktif -> statusStr = resources.getString(R.string.tidak_aktif).trim()
                }
            }
        })


        dialogBinding.btnFilter.setOnClickListener{
            populateMahasiswa(prodiStr,angkatanStr,statusStr,false)
            dialog.dismiss()
        }

        dialog.setContentView(dialogBinding.root)
        dialog.show()
    }
}