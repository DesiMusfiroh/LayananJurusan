package com.layanan.jurusan.ui.civitas

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.DosenModel
import com.layanan.jurusan.data.model.Mahasiswa
import com.layanan.jurusan.databinding.DialogFilterDosenBinding
import com.layanan.jurusan.databinding.DialogFilterMahasiswaBinding
import com.layanan.jurusan.databinding.FragmentDosenBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class DosenFragment : Fragment() {
    private lateinit var binding: FragmentDosenBinding
    private lateinit var viewModel: CivitasViewModel
    private lateinit var dataResponse: List<DosenModel>
    private lateinit var adapter: DosenAdapter
    private var statusDosenAdapter: ArrayAdapter<String>? = null
    private var statusDosenStr = "Semua"
    private var prodiStr = "Semua"
    var prodiCheckedId: Int = R.id.prodi_semua


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDosenBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[CivitasViewModel::class.java]
        populateDosen("Semua","Semua",false)
        setupSearchView()

        binding.btnFilter.setOnClickListener {
            showFilterDialog()
        }
    }

    private fun setupSearchView() {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searhDosen.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        binding.searhDosen.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.setSearchQuery(query,"dosen")
                    populateDosen(prodiStr,statusDosenStr,true)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.setSearchQuery(newText,"dosen")
                    populateDosen(prodiStr,statusDosenStr,true)
                }
                return true
            }

        })

    }

    private fun populateDosen(prodi: String, status: String, isSearch: Boolean?){
        if (isSearch == false){
            viewModel.getDosen(prodi,status).observe(viewLifecycleOwner,{
                dataResponse = it
                adapter = DosenAdapter(dataResponse)
                adapter.notifyDataSetChanged()
                binding.apply {
                    rvDosen.layoutManager = LinearLayoutManager(context)
                    rvDosen.setHasFixedSize(true)
                    rvDosen.adapter = adapter
                    shimmerRvCivitas.stopShimmer()
                    shimmerRvCivitas.visibility = View.GONE
                    rvDosen.visibility = View.VISIBLE
                }
            })
        }else{
            viewModel.getSearchDosen(prodi,status).observe(viewLifecycleOwner,{
                dataResponse = it
                adapter = DosenAdapter(dataResponse)
                adapter.notifyDataSetChanged()
                binding.apply {
                    rvDosen.layoutManager = LinearLayoutManager(context)
                    rvDosen.setHasFixedSize(true)
                    rvDosen.adapter = adapter
                }
            })
        }
    }

    private fun showFilterDialog() {
        val dialog = BottomSheetDialog(requireContext())
        val dialogBinding = DialogFilterDosenBinding.inflate(layoutInflater)
        val rgProdi = dialogBinding.radioGroupProdi
        var prodiRbChecked = rgProdi.findViewById<RadioButton>(prodiCheckedId)

        prodiRbChecked.performClick()


        viewModel.getStatusDosen().observe(viewLifecycleOwner,{listStatusDosen->
            statusDosenAdapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, listStatusDosen) }
            dialogBinding.spinnerStatus.adapter = statusDosenAdapter
            dialogBinding.spinnerStatus.post {
                val statusDosenSelectedIndex = listStatusDosen.indexOf(statusDosenStr)
                dialogBinding.spinnerStatus.setSelection(statusDosenSelectedIndex)
            }
            dialogBinding.spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    statusDosenStr = dialogBinding.spinnerStatus.selectedItem.toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        })

        rgProdi.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                prodiCheckedId = rgProdi.checkedRadioButtonId
                when(prodiCheckedId){
                    R.id.prodi_semua -> prodiStr = resources.getString(R.string.semua).trim()
                    R.id.prodi_si -> prodiStr = resources.getString(R.string.sistem_informasi).trim()
                    R.id.prodi_te -> prodiStr = resources.getString(R.string.teknik_elekto).trim()
                }
            }

        })

        dialogBinding.btnFilter.setOnClickListener {
            populateDosen(prodiStr,statusDosenStr,false)
            dialog.dismiss()
        }

        dialog.setContentView(dialogBinding.root)
        dialog.show()
    }

}