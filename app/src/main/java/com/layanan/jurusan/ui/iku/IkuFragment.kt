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
import com.layanan.jurusan.databinding.FragmentIkuBinding
import com.layanan.jurusan.utils.YearPickerDialog
import java.text.SimpleDateFormat
import java.util.*


class IkuFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: FragmentIkuBinding
    private lateinit var yearText: String
    private val format = SimpleDateFormat("yyyy",Locale.getDefault())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentIkuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardIku1.setOnClickListener {
            val iku1Intent = Intent(context, Iku1Activity::class.java)
            iku1Intent.putExtra(Iku1Activity.EXTRA_YEAR,yearText)
            startActivity(iku1Intent)
        }
        binding.cardIku2.setOnClickListener {
            val iku2Intent = Intent(context, Iku2Activity::class.java)
            iku2Intent.putExtra(Iku2Activity.EXTRA_YEAR,yearText)
            startActivity(iku2Intent)
        }
        binding.cardIku3.setOnClickListener {
            val iku3Intent = Intent(context, Iku3Activity::class.java)
            iku3Intent.putExtra(Iku3Activity.EXTRA_YEAR, yearText)
            startActivity(iku3Intent)
        }
        binding.cardIku4.setOnClickListener {
            val iku4Intent = Intent(context, Iku4Activity::class.java)
            iku4Intent.putExtra(Iku4Activity.EXTRA_YEAR, yearText)
            startActivity(iku4Intent)
        }
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

    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR,p1)
        yearText = format.format(calendar.time)
        Log.d("Tahun",yearText)
    }



}