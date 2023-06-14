package com.bangkit.myacne.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.myacne.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(){
        private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener{
            finish()
        }
    }
}