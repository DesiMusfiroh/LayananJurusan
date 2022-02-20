package com.layanan.jurusan.ui.civitas

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
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.Mahasiswa
import com.layanan.jurusan.databinding.DialogFilterAlumniBinding
import com.layanan.jurusan.databinding.DialogFilterMahasiswaBinding
import com.layanan.jurusan.databinding.FragmentAlumniBinding
import com.layanan.jurusan.databinding.FragmentDosenBinding
import com.layanan.jurusan.ui.news.NewsLoadStateAdapter
import com.layanan.jurusan.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class AlumniFragment : Fragment() {

    private lateinit var binding: FragmentAlumniBinding
    private lateinit var viewModel: CivitasViewModel
    private lateinit var alumniAdapter: AlumniAdapter
    var prodiStr = "Semua"
    var angkatanStr = "Semua"
    private lateinit var dataResponse: List<Mahasiswa>
    private var angkatanAdapter: ArrayAdapter<String>? = null
    var prodiCheckedId: Int = R.id.prodi_semua_alumni
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAlumniBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[CivitasViewModel::class.java]
        populateAlumni(prodiStr, angkatanStr,false)

        binding.btnFilter.setOnClickListener {
            showFilterDialog()
        }
    }

    private fun populateAlumni(prodi: String, angkatan: String, isSearch: Boolean){
        if(isSearch == false){
            alumniAdapter = AlumniAdapter()
            binding.rvAlumni.apply {
                adapter = alumniAdapter.withLoadStateHeaderAndFooter(
                    header = NewsLoadStateAdapter{alumniAdapter.retry()},
                    footer = NewsLoadStateAdapter{alumniAdapter.retry()}
                )

                alumniAdapter.addLoadStateListener { loadState ->

//                    binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    isVisible = loadState.source.refresh is LoadState.NotLoading
                    binding.btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                    if(loadState.source.refresh is LoadState.NotLoading){
                        binding.shimmerRvCivitas.stopShimmer()
                        binding.shimmerRvCivitas.visibility = View.GONE
                        binding.rvAlumni.visibility = View.VISIBLE
                    }

                    if(loadState.source.refresh is LoadState.Loading){
                        binding.shimmerRvCivitas.isShimmerVisible
                        binding.shimmerRvCivitas.visibility = View.VISIBLE
                        binding.rvAlumni.visibility = View.GONE
                    }

                    // No results found
                    if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        alumniAdapter.itemCount < 1
                    ) {
                        isVisible = false
                        binding.tvNoSearchResult.isVisible = true
                    } else {
                        binding.tvNoSearchResult.isVisible = false
                    }
                }
                layoutManager = LinearLayoutManager(requireActivity())
                setHasFixedSize(true)
            }


            lifecycleScope.launch {
                viewModel.getAlumni(prodi, angkatan).collectLatest {
                    alumniAdapter.notifyDataSetChanged()
                    alumniAdapter.submitData(it)
                    binding.shimmerRvCivitas.stopShimmer()
                    binding.shimmerRvCivitas.visibility = View.GONE
                    binding.rvAlumni.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showFilterDialog() {
        Log.d("ProdiRbID",prodiCheckedId.toString())
        val dialog = BottomSheetDialog(requireContext())
        val dialogBinding = DialogFilterAlumniBinding.inflate(layoutInflater)
        val rgProdi = dialogBinding.radioGroupProdi
        var prodiRbChecked = rgProdi.findViewById<RadioButton>(prodiCheckedId)

        prodiRbChecked!!.performClick()


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
                    R.id.prodi_semua_alumni -> prodiStr = resources.getString(R.string.semua).trim()
                    R.id.prodi_si_alumni -> prodiStr = resources.getString(R.string.sistem_informasi).trim()
                    R.id.prodi_te_alumni -> prodiStr = resources.getString(R.string.teknik_elekto).trim()
                }
            }

        })

        dialogBinding.btnFilter.setOnClickListener{
            populateAlumni(prodiStr,angkatanStr,false)
            dialog.dismiss()
        }

        dialog.setContentView(dialogBinding.root)
        dialog.show()
    }
}