package com.layanan.jurusan.ui.profile

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.github.gcacace.signaturepad.views.SignaturePad
import com.layanan.jurusan.databinding.ActivitySignatureBinding
import com.layanan.jurusan.ui.prodi.ProdiViewModel
import com.layanan.jurusan.viewmodel.ViewModelFactory
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.*
import java.util.*

class SignatureActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignatureBinding
    private var WRITE_EXTERNAL_STORAGE_PERMISSION_CODE: Int = 1
    private var READ_EXTERNAL_STORAGE_PERMISSION_CODE: Int = 2
    private lateinit var mSignatureBitmap: Bitmap
    private lateinit var mSignature: SignaturePad
    private lateinit var viewModel: SignatureViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Tanda Tangan Digital"
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[SignatureViewModel::class.java]

        checkPermission()
        setUpView()
    }

    private fun checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when (PackageManager.PERMISSION_DENIED) {
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                    requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        WRITE_EXTERNAL_STORAGE_PERMISSION_CODE
                    )
                }
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    requestPermissions(
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        READ_EXTERNAL_STORAGE_PERMISSION_CODE
                    )
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            WRITE_EXTERNAL_STORAGE_PERMISSION_CODE -> if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Anda perlu memberikan semua izin untuk menggunakan aplikasi ini.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            READ_EXTERNAL_STORAGE_PERMISSION_CODE -> if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Anda perlu memberikan semua izin untuk menggunakan aplikasi ini.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    fun setUpView(){
        mSignature = binding.signaturePad

        binding.btnClear.setOnClickListener {
            mSignature.clear()
        }

        val userPref = this.getSharedPreferences("user",
            AppCompatActivity.MODE_PRIVATE
        )

        val jwtToken = userPref?.getString("token","devicetoken")
        if (jwtToken != null) {
            Log.d("Jwt",jwtToken)
        }

        binding.btnSaveSignature.setOnClickListener {
            mSignatureBitmap = mSignature.signatureBitmap
            val fileName = getFileName()
//            val imgPath = saveImageToDevice(mSignatureBitmap,fileName)

            val imagePart = buildImageBodyPart(getFileName(),mSignatureBitmap)
            viewModel.uploadSignature(imagePart,jwtToken!!).observe(this,{
                Log.d("SignatureActivity",it.toString())
                Toast.makeText(this, "Berhasil memperbarui tanda tangan", Toast.LENGTH_SHORT).show()
                finish()
            })
        }
    }

    private fun getFileName():String{
        return "${Calendar.getInstance().timeInMillis}.jpg"
    }

    private fun saveImageToDevice(myBitmap: Bitmap, nameFile: String): String {
        return MediaStore.Images.Media.insertImage(contentResolver, myBitmap, nameFile, null)
    }


    private fun buildImageBodyPart(fileName: String, bitmap: Bitmap):  MultipartBody.Part {
        val leftImageFile = convertBitmapToFile(fileName, bitmap)
        val reqFile = RequestBody.create("image/*".toMediaTypeOrNull(),    leftImageFile)
        return MultipartBody.Part.createFormData("signature",     leftImageFile.name, reqFile)
    }

    private fun convertBitmapToFile(fileName: String, bitmap: Bitmap): File {
        //create a file to write bitmap data
        val file = File(this.cacheDir, fileName)
        file.createNewFile()

        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos)
        val bitMapData = bos.toByteArray()

        //write the bytes in file
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try {
            fos?.write(bitMapData)
            fos?.flush()
            fos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }
}