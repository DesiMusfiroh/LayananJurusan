package com.layanan.jurusan.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.layanan.jurusan.R

class ShowImageDialog(val resource: Int,) : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView: View = inflater.inflate(resource, null)
        val img = dialogView.findViewById<ImageView>(R.id.opened_image)
        img.setImageResource(R.drawable.example_permohonan_surat)
        builder.setView(dialogView)

        return builder.create()
    }
}

//class ShowImageDialog(val resource: Int) : DialogFragment(){
//    private lateinit var mScaleGestureDetector: ScaleGestureDetector
//    private var mScaleFactor = 1.0f
//    private lateinit var img: ImageView
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val builder = AlertDialog.Builder(requireActivity())
//        val inflater = requireActivity().layoutInflater
//        val dialogView: View = inflater.inflate(resource, null)
//        img = dialogView.findViewById<ImageView>(R.id.opened_image)
//        img.setImageResource(R.drawable.example_permohonan_surat)
//        builder.setView(dialogView)
//
//        mScaleGestureDetector = ScaleGestureDetector(requireActivity(), ScaleListener())
//        setListener()
//
//        return builder.create()
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    fun setListener(){
//        img?.setOnTouchListener(object : View.OnTouchListener{
//            override fun onTouch(p0: View?, motionEvent: MotionEvent?): Boolean {
//                mScaleGestureDetector.onTouchEvent(motionEvent);
//                return true
//            }
//
//        })
//    }
//
//    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
//        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
//            mScaleFactor *= scaleGestureDetector.scaleFactor
//            mScaleFactor = Math.max(
//                0.1f,
//                Math.min(mScaleFactor, 10.0f)
//            )
//            img.setScaleX(mScaleFactor)
//            img.setScaleY(mScaleFactor)
//            return true
//        }
//    }
//
//
//}