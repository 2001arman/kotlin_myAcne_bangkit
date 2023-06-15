package com.bangkit.myacne.ui.camera_page

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.myacne.databinding.ActivityCameraResultBinding
import com.bangkit.myacne.utils.reduceFileImage
import com.bangkit.myacne.utils.rotateBitmap
import com.bangkit.myacne.utils.uriToFile
import java.io.File

class CameraResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraResultBinding
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultCode = intent.getIntExtra("resultCode", 0)

        if (resultCode == CAMERA_X_RESULT) {
            val myFile = intent.getSerializableExtra("pictureCamera") as File
            val isBackCamera = intent.getBooleanExtra("isBackCamera", true) as Boolean
            val result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                isBackCamera
            )
            val file = reduceFileImage(result, myFile)
            getFile = file
            binding.previewImageView.setImageBitmap(result)
        } else if (resultCode == GALLERY_X_RESULT) {
            startGallery()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this)
            getFile = myFile
            val results = BitmapFactory.decodeFile(myFile.path)
            binding.previewImageView.setImageURI(selectedImg)
        }
    }//            binding.previewIV.setImageURI(selectedImg)

    companion object {
        const val CAMERA_X_RESULT = 200
        const val GALLERY_X_RESULT = 201
    }
}