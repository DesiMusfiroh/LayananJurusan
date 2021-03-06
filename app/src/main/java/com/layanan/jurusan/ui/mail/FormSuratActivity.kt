package com.layanan.jurusan.ui.mail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.marginTop
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.JenisSuratModel
import com.layanan.jurusan.data.model.KeywordSuratModel
import com.layanan.jurusan.databinding.ActivityFormSuratBinding
import com.layanan.jurusan.viewmodel.ViewModelFactory
import com.loopj.android.http.RequestParams

class FormSuratActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormSuratBinding
    private lateinit var viewModel: FormSuratViewModel
    private lateinit var keywordObjects: List<KeywordSuratModel>
    private var listJenisTtd: ArrayList<JenisTtd> = ArrayList()
    private var editTextIdMap = HashMap<String,Int>()
    private var arrKeyword = ArrayList<String>()
    private lateinit var suratExtra: JenisSuratModel
    private lateinit var spinnerAdapter: JenisTandaTanganSpinnerAdapter
    private var spinnerSelectedValue = "pilih"
    companion object {
        const val EXTRA_MAIL = "mail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormSuratBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[FormSuratViewModel::class.java]

        suratExtra = intent.getParcelableExtra<JenisSuratModel>(EXTRA_MAIL) as JenisSuratModel
        binding.tvTitle.text = suratExtra.judul

        viewModel.setJenisSuratId(suratExtra.id)
        viewModel.getKeywordSurat().observe(this,{
            keywordObjects = it
            Log.d("KeywordSurat",it.toString())
            val requestParams = RequestParams()
            for(item in it){
                if (item.tipe  == "manual"){
                    item.keyword?.let { it1 -> arrKeyword.add(it1) }
                    val editTextParam = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    val editText = TextInputEditText( this)
                    val textInputLayout = TextInputLayout(this,null, R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox)
                    val textInputLayoutParam = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    textInputLayoutParam.marginStart = 1
                    textInputLayoutParam.setMargins(
                        20, 10,20,10
                    )

                    val editTextId = View.generateViewId()
                    editTextIdMap.put(item.keyword!!,editTextId)
                    editText.id = editTextId

                    Log.d("MapId",editTextIdMap.get(item.keyword).toString())

                    textInputLayout.layoutParams = textInputLayoutParam
//                    textInputLayout.defaultHintTextColor = resources.getColor(R.color.primary)
                    textInputLayout.addView(editText,editTextParam)
                    textInputLayout.hint = item.nama
                    binding.containerForm.addView(textInputLayout)
                }
            }

            listJenisTtd.add(JenisTtd("pilih","Pilih Tanda Tangan"))
            listJenisTtd.add(JenisTtd("ttd_qr","QR Code"))
            listJenisTtd.add(JenisTtd("ttd_digital","Tanda Tangan Digital"))
            listJenisTtd.add(JenisTtd("ttd_langsung", "Tanda Tangan Langsung"))

            spinnerAdapter = JenisTandaTanganSpinnerAdapter(this, R.layout.item_spinner, R.id.tv_item_spinner,listJenisTtd)
            binding.spinnerJenisTtd.adapter = spinnerAdapter
            submitForm()
        })

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    fun submitForm(){

        val requestParam = RequestParams()
        requestParam.put("jenis_surat_id",suratExtra.id)
        requestParam.put("tipe_surat",suratExtra.tipe)
        requestParam.put("arr_keyword",arrKeyword)

        with(binding){
            for (item in keywordObjects){
                if(item.tipe == "manual"){
                    val editText = findViewById<TextInputEditText>(editTextIdMap.get(item.keyword)!!)
                    val param = editText.text.toString().trim()
                    requestParam.put(item.keyword,param)
                    editText.addTextChangedListener(object: TextWatcher{
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            requestParam.put(item.keyword,editText.text.toString().trim())
                            Log.d("ParamsChange",requestParam.toString())
                        }

                        override fun afterTextChanged(p0: Editable?) {

                        }

                    })
                }
            }

            binding.spinnerJenisTtd.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (position != 0){
                        val jenisTtd = spinnerAdapter.getItem(position)
                        spinnerSelectedValue = jenisTtd?.value.toString()
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

            binding.btnSavePermohonan.setOnClickListener {
                for (item in keywordObjects){
                    if (item.tipe == "manual"){
                        val editText = findViewById<TextInputEditText>(editTextIdMap.get(item.keyword)!!)
                        val value = editText.text.toString().trim()
                        if (value.isEmpty()){
                            editText.error = "Tidak boleh kosong"
                            return@setOnClickListener
                        }
                    }
                }
                if (spinnerSelectedValue == "pilih"){
                    Toast.makeText(this@FormSuratActivity, "Pilih jenis tanda tangan", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val userPref = this@FormSuratActivity.getSharedPreferences("user",
                    AppCompatActivity.MODE_PRIVATE
                )

                val jwtToken = userPref?.getString("token","devicetoken")
                if (jwtToken != null) {
                    Log.d("Jwt",jwtToken)
                }
                Log.d("JwtToken",jwtToken!!)


                viewModel.storePermohonanSurat(requestParam, jwtToken).observe(this@FormSuratActivity,{
                    if (it == "success"){
                        Toast.makeText(this@FormSuratActivity, "Berhasil", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this@FormSuratActivity, it, Toast.LENGTH_SHORT).show()
                    }
                })

            }
        }

    }
}