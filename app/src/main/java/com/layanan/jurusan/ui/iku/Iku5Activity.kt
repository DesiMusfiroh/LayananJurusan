package com.layanan.jurusan.ui.iku

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.layanan.jurusan.R
import com.layanan.jurusan.data.model.Iku1Model
import com.layanan.jurusan.data.model.Iku5Model
import com.layanan.jurusan.databinding.ActivityIku5Binding
import com.layanan.jurusan.databinding.DialogIku5Binding
import com.layanan.jurusan.databinding.ItemIku5Binding
import com.layanan.jurusan.viewmodel.ViewModelFactory

class Iku5Activity : AppCompatActivity() {
    private lateinit var binding: ActivityIku5Binding
    private lateinit var viewModel: IkuViewModel
    private lateinit var adapter: Iku5Adapter
    private lateinit var dataResponse: List<Iku5Model>
    companion object {
        const val EXTRA_YEAR = "2022"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIku5Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[IkuViewModel::class.java]

        val extras = intent.extras
        val year = extras?.getString(EXTRA_YEAR,"0")
        Log.d("ExtraTahun",year!!)
        populateIku5(year)
    }

    private fun populateIku5(year: String){
        viewModel.getIku5(year).observe(this,{
            Log.d("DataActivity",it.toString())
            dataResponse = it
            if (it != null){
                adapter = Iku5Adapter(it)
                adapter.notifyDataSetChanged()
                binding.apply {
                    rvIku5.layoutManager = LinearLayoutManager(this@Iku5Activity)
                    rvIku5.setHasFixedSize(true)
                    rvIku5.adapter = adapter
                }
                adapter
                adapter.setOnItemClickCallback(object : Iku5Adapter.OnItemClickCallback {
                    override fun onItemClicked(data: Iku5Model) {
                        showDialogData(data)
                    }
                })
            }
        })
    }

    fun showDialogData(data: Iku5Model){
        val dialog = BottomSheetDialog(this)
        val dialogIku5Binding = DialogIku5Binding.inflate(layoutInflater)

        dialogIku5Binding.apply {
            tvNama.text = data.nama
            tvNoInduk.text = data.noInduk
            tvJenisKegiatan.text = data.hasilKerjaDosen?.jenisKegiatan
            tvJudul.text = data.hasilKerjaDosen?.judul

            tvLuaranHasil.text = data.hasilKerjaDosen?.luaranHasil
            tvLink.text = data.hasilKerjaDosen?.link
            tvKutipan.text = data.hasilKerjaDosen?.jumlahKutipan
            tvSitasiIndex.text = data.hasilKerjaDosen?.sitasiI10Index

            tvJenisLembaga.text = data.penerapanDiMasyarakat?.jenisLembaga
            tvNamaLembagaPenerap.text = data.penerapanDiMasyarakat?.namaLembaga
            tvNoPks.text = data.penerapanDiMasyarakat?.noPks

            tvTipeKolaborator.text = data.kolaborator?.tipeKolaborator
            tvLembagaKolaborator.text = data.kolaborator?.lembagaKolaborator

            tvTipePenghargaan.text = data.karyaTerapan?.tipePenghargaan
            tvPemberiPenghargaan.text = data.karyaTerapan?.pemberiPenghargaan

            tvTipePendanaan.text = data.karyaSeni?.tipePendanaan
            tvNamaLembagaKaryaSeni.text = data.karyaSeni?.namaLembaga
            tvKriteriaLuaran.text = data.karyaSeni?.kriteriaLuaran
            tvMetodeBerkarya.text = data.karyaSeni?.metodeBerkarya
            tvStudiKasus.text = data.karyaSeni?.studiKasus
            tvSubstantialReview.text = data.karyaSeni?.substantialReview
            tvLinkReview.text = data.karyaSeni?.linkReview

            if(data.penerapanDiMasyarakat?.jenisLembaga != null || data.penerapanDiMasyarakat?.namaLembaga != null || data.penerapanDiMasyarakat?.noPks != null){
                tablePenerapanDiMasyarakat.visibility = View.VISIBLE
            }else{
                tablePenerapanDiMasyarakat.visibility = View.GONE
            }

            if(data.kolaborator?.tipeKolaborator != null || data.kolaborator?.lembagaKolaborator != null){
                tableKolaborator.visibility = View.VISIBLE
            }else{
                tableKolaborator.visibility = View.GONE
            }

            if(data.karyaTerapan?.tipePenghargaan != null || data.karyaTerapan?.pemberiPenghargaan != null){
                tableKaryaTerapan.visibility = View.VISIBLE
            }else{
                tableKaryaTerapan.visibility = View.GONE
            }

            if (data.karyaSeni?.tipePendanaan != null || data.karyaSeni?.namaLembaga != null || data.karyaSeni?.kriteriaLuaran !=  null || data.karyaSeni?.metodeBerkarya != null || data.karyaSeni?.studiKasus != null){
                tableKaryaSeni.visibility = View.VISIBLE
            }else{
                tableKaryaSeni.visibility = View.GONE
            }


            btnBukti.setOnClickListener {
                val url = data.bukti
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogIku5Binding.root)
        dialog.show()
    }
}