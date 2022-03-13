package com.layanan.jurusan.ui.document

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.layanan.jurusan.data.model.DocumentModel
import com.layanan.jurusan.databinding.ActivityListDocumentBinding
import com.layanan.jurusan.databinding.DialogDocumentBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory
import com.loopj.android.http.AsyncHttpClient.log

class ListDocumentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListDocumentBinding
    private lateinit var viewModel: DocumentViewModel
    private lateinit var adapter: DocumentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDocumentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DocumentViewModel::class.java]

        populateDocument()
    }

    private fun populateDocument() {
        viewModel.getListDocument().observe(this,{
            log.d("Document", it.toString())
            adapter = DocumentAdapter(it)
            adapter.notifyDataSetChanged()

            with(binding){
                rvDocument.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                rvDocument.setHasFixedSize(true)
                rvDocument.adapter = adapter
            }
            adapter.setOnItemClickCallback(object : DocumentAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DocumentModel) {
                    showDialogData(data)
                }
            })

            binding.btnBack.setOnClickListener {
                onBackPressed()
            }
        })
    }

    private fun showDialogData(data: DocumentModel) {
        val dialog = BottomSheetDialog(this)
        val dialogDocument = DialogDocumentBinding.inflate(layoutInflater)
        dialogDocument.apply {
            tvTitle.text = data.title
            tvDesc.text =  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                Html.fromHtml(data.desc, Html.FROM_HTML_MODE_COMPACT)
            }else{
                Html.fromHtml(data.desc)
            }
            btnDownload.setOnClickListener {
                val url = data.file
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogDocument.root)
        dialog.show()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}
