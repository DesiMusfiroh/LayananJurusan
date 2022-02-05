package com.layanan.jurusan.ui.mail

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.layanan.jurusan.data.model.JenisSuratModel
import com.layanan.jurusan.data.model.RiwayatSuratModel
import com.layanan.jurusan.databinding.FragmentMailBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class MailFragment : Fragment() {
    private lateinit var binding: FragmentMailBinding
    private lateinit var viewModel: MailViewModel
    private lateinit var jenisSuratAdapter: JenisSuratAdapter
    private lateinit var riwayatSuratAdapter: RiwayatSuratAdapter
    private lateinit var jenisSuratResponse: List<JenisSuratModel>
    private lateinit var riwayatSuratResponse: List<RiwayatSuratModel>
    private var tipeSurat: String = "internal"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MailViewModel::class.java]

        binding.radioGroupTipeSurat.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                val tipeSuratId = binding.radioGroupTipeSurat.checkedRadioButtonId
                tipeSurat = resources.getResourceEntryName(tipeSuratId).toLowerCase()
                showJenisSurat(tipeSurat)
            }
        })


        binding.btnRiwayatSurat.setOnClickListener {
            val intent = Intent(activity,ListRiwayatSuratActivity::class.java)
            startActivity(intent)
        }
        showJenisSurat(tipeSurat)
    }

    private fun showJenisSurat(tipe: String) {
        viewModel.getJenisSurat(tipe).observe(requireActivity(),{
            jenisSuratResponse = it
            jenisSuratAdapter = JenisSuratAdapter(jenisSuratResponse, requireContext())
            jenisSuratAdapter.notifyDataSetChanged()

            with(binding){
                rvJenisSurat.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                rvJenisSurat.setHasFixedSize(true)
                rvJenisSurat.adapter = jenisSuratAdapter
            }
            jenisSuratAdapter.setOnItemClickCallback(object : JenisSuratAdapter.OnItemClickCallback {
                override fun onItemClicked(data: JenisSuratModel) {
                    val intent = Intent(activity, FormSuratActivity::class.java)
                    intent.putExtra(FormSuratActivity.EXTRA_MAIL, data)
                    startActivity(intent)
                }
            })
        })
    }
}


