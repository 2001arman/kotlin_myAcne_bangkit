package com.bangkit.myacne.ui.result_prediction_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.myacne.R
import com.bangkit.myacne.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val level = intent.getStringExtra(LEVEL)
        binding.tvAcneLevel.text = level
    }

    companion object{
        const val LEVEL = "level"
    }
}