package com.layanan.jurusan.utils


import android.app.AlertDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.layanan.jurusan.R
import java.util.*


class YearPickerDialog : DialogFragment() {
    private var listener: OnDateSetListener? = null
    fun setListener(listener: OnDateSetListener?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        // Get the layout inflater
        val inflater = requireActivity().layoutInflater
        val cal = Calendar.getInstance()
        val dialog: View = inflater.inflate(R.layout.year_picker_dialog, null)
//        val monthPicker = dialog.findViewById<View>(R.id.picker_month) as NumberPicker
        val yearPicker = dialog.findViewById<View>(R.id.picker_year) as NumberPicker
//        monthPicker.minValue = 0
//        monthPicker.maxValue = 11
//        monthPicker.value = cal[Calendar.MONTH]
        val year = cal[Calendar.YEAR]
        yearPicker.minValue = year
        yearPicker.maxValue = MAX_YEAR
        yearPicker.value = year
        builder.setView(dialog) // Add action buttons
            .setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->
                    listener!!.onDateSet(
                        null,
                        yearPicker.value,
                        0,
                        0
                    )
                })
            .setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id -> this@YearPickerDialog.dialog!!.cancel() })
        return builder.create()
    }

    companion object {
        private const val MAX_YEAR = 2099
    }
}