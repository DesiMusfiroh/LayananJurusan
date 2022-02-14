package com.layanan.jurusan.ui.mail

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.JenisSuratModel
import com.layanan.jurusan.ui.news.NewsLoadStateAdapter
import com.layanan.jurusan.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MailFragment : Fragment() {
    private lateinit var rvJenisSurat: RecyclerView
    private lateinit var rvRiwayatSuratDosen: RecyclerView
    private lateinit var radioGroupTipeSurat: RadioGroup
    private lateinit var btnRiwayatSurat: Button
    private lateinit var viewModel: MailViewModel
    private lateinit var jenisSuratAdapter: JenisSuratAdapter
    private lateinit var riwayatSuratDosenAdapter: ListRiwayatSuratDosenAdapter
    private lateinit var jenisSuratResponse: List<JenisSuratModel>
    private lateinit var userPref: SharedPreferences
    private var role: Int? = 2 //default role mahasiswa
    private var tipeSurat: String = "internal"
    private var jwtToken = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userPref = requireActivity().getSharedPreferences("user",
            AppCompatActivity.MODE_PRIVATE
        )
        role = userPref.getInt("role",2)
        jwtToken = userPref.getString("token","devicetoken")!!
        Log.d("TestCoba",jwtToken)
        if (role == 2){
            return inflater.inflate(R.layout.fragment_mail,container,false)
        }else{
            return inflater.inflate(R.layout.fragment_mail_dosen,container,false)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MailViewModel::class.java]

        if (role == 2){
            setupViewMahasiswa()
        }else{
            setupViewDosen()
        }

    }

    private fun showJenisSurat(tipe: String) {
        viewModel.getJenisSurat(tipe).observe(requireActivity(),{
            jenisSuratResponse = it
            jenisSuratAdapter = JenisSuratAdapter(jenisSuratResponse, requireContext())
            jenisSuratAdapter.notifyDataSetChanged()

            rvJenisSurat.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rvJenisSurat.setHasFixedSize(true)
            rvJenisSurat.adapter = jenisSuratAdapter
            jenisSuratAdapter.setOnItemClickCallback(object : JenisSuratAdapter.OnItemClickCallback {
                override fun onItemClicked(data: JenisSuratModel) {
                    val intent = Intent(activity, FormSuratActivity::class.java)
                    intent.putExtra(FormSuratActivity.EXTRA_MAIL, data)
                    startActivity(intent)
                }
            })
        })
    }

    private fun setupViewMahasiswa(){
        rvJenisSurat = view?.findViewById<RecyclerView>(R.id.rv_jenis_surat)!!
        radioGroupTipeSurat = view?.findViewById(R.id.radio_group_tipe_surat)!!
        btnRiwayatSurat = view?.findViewById(R.id.btn_riwayat_surat)!!
        radioGroupTipeSurat.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                val tipeSuratId = radioGroupTipeSurat.checkedRadioButtonId
                tipeSurat = resources.getResourceEntryName(tipeSuratId).toLowerCase()
                showJenisSurat(tipeSurat)
            }
        })


        btnRiwayatSurat.setOnClickListener {
            val intent = Intent(activity,ListRiwayatSuratActivity::class.java)
            startActivity(intent)
        }
        showJenisSurat(tipeSurat)
    }

    private fun setupViewDosen(){
        rvRiwayatSuratDosen = view?.findViewById(R.id.rv_riwayat_surat_dosen)!!
        riwayatSuratDosenAdapter = ListRiwayatSuratDosenAdapter()
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBarRiwayatSuratDosen)!!
        val btnRetry = view?.findViewById<Button>(R.id.btnRetryRiwayatSuratDosen)!!
        val tvNoSearchResult = view?.findViewById<TextView>(R.id.tvNoSearchResultRiwayatSuratDosen)!!

        rvRiwayatSuratDosen.adapter = riwayatSuratDosenAdapter.withLoadStateHeaderAndFooter(
            header = NewsLoadStateAdapter{riwayatSuratDosenAdapter.retry()},
            footer = NewsLoadStateAdapter{riwayatSuratDosenAdapter.retry()}
        )
        riwayatSuratDosenAdapter.addLoadStateListener {loadState ->

            progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            rvRiwayatSuratDosen.isVisible = loadState.source.refresh is LoadState.NotLoading
            btnRetry.isVisible = loadState.source.refresh is LoadState.Error

            // No results found
            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached &&
                riwayatSuratDosenAdapter.itemCount < 1
            ) {
                rvRiwayatSuratDosen.isVisible = false
                tvNoSearchResult.isVisible = true
            } else {
                tvNoSearchResult.isVisible = false
            }
        }
        rvRiwayatSuratDosen.layoutManager = LinearLayoutManager(requireActivity())
        rvRiwayatSuratDosen.setHasFixedSize(true)

        lifecycleScope.launch {
            viewModel.getRiwayatSuratDosen("Bearer ${jwtToken}").collectLatest {
                riwayatSuratDosenAdapter.submitData(it)
            }
        }

    }
}


