package com.layanan.jurusan.ui.iku

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.layanan.jurusan.data.model.MatriksIkuModel
import com.layanan.jurusan.databinding.FragmentIkuBinding
import com.layanan.jurusan.ui.mail.MailViewModel
import com.layanan.jurusan.utils.YearPickerDialog
import com.layanan.jurusan.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class IkuFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: FragmentIkuBinding
    private lateinit var yearText: String
    private lateinit var viewModel: IkuViewModel
    private val format = SimpleDateFormat("yyyy",Locale.getDefault())
    private lateinit var adapter: MatriksIkuAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentIkuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[IkuViewModel::class.java]


        binding.btnFilter.setOnClickListener {
            val pd = YearPickerDialog()
            pd.setListener(this)
            pd.show(requireFragmentManager(),"MonthYearPickerDialog")
        }


        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        cal.set(Calendar.YEAR,year)

        yearText = format.format(cal.time)
        Log.d("Tahun",yearText)

        populateIku()

    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR,p1)
        yearText = format.format(calendar.time)
        Log.d("Tahun",yearText)
        populateIku()
    }

    fun populateIku(){
        viewModel.getMatriksIku(yearText.toInt()).observe(viewLifecycleOwner) {
            adapter = MatriksIkuAdapter(it)
            adapter.notifyDataSetChanged()
            binding.apply {
                rvMatriks.layoutManager = LinearLayoutManager(requireActivity())
                rvMatriks.setHasFixedSize(true)
                rvMatriks.adapter = adapter
            }
            adapter.setOnItemClickCallback(object: MatriksIkuAdapter.OnItemClickCallback{
                override fun onItemClicked(data: MatriksIkuModel) {
                    when(data.iku){
                        "IKU 1" -> {
                            val intent = Intent(requireActivity(),Iku1Activity::class.java)
                            intent.putExtra(Iku1Activity.EXTRA_YEAR,yearText)
                            startActivity(intent)
                        }
                        "IKU 2" -> {
                            val intent = Intent(requireActivity(),Iku2Activity::class.java)
                            intent.putExtra(Iku2Activity.EXTRA_YEAR,yearText)
                            startActivity(intent)
                        }
                        "IKU 3" -> {
                            val intent = Intent(requireActivity(),Iku3Activity::class.java)
                            intent.putExtra(Iku3Activity.EXTRA_YEAR,yearText)
                            startActivity(intent)
                        }
                        "IKU 4" -> {
                            val intent = Intent(requireActivity(), Iku4Activity::class.java)
                            intent.putExtra(Iku4Activity.EXTRA_YEAR,yearText)
                            startActivity(intent)
                        }
                        "IKU 5" -> {
                            val intent = Intent(requireActivity(),Iku5Activity::class.java)
                            intent.putExtra(Iku5Activity.EXTRA_YEAR,yearText)
                            startActivity(intent)
                        }
                        "IKU 6" -> {
                            val intent = Intent(requireActivity(),Iku6Activity::class.java)
                            intent.putExtra(Iku6Activity.EXTRA_YEAR,yearText)
                            startActivity(intent)
                        }
                        "IKU 7" -> {
                            val intent = Intent(requireActivity(), Iku1Activity::class.java)
                            intent.putExtra(Iku7Activity.EXTRA_YEAR,yearText)
                            startActivity(intent)
                        }
                        "IKU 8" -> {
                            val intent = Intent(requireActivity(),Iku8Activity::class.java)
                            intent.putExtra(Iku8Activity.EXTRA_YEAR,yearText)
                            startActivity(intent)
                        }
                    }
                }

            })
        }
    }

}