package com.c0d3v9.adopet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.c0d3v9.adopet.databinding.ActivityLoginBinding
import com.c0d3v9.adopet.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mvToSignup.setOnClickListener { startActivity(Intent(this@LoginActivity, SignupActivity::class.java)) }

        binding.loginBtn.setOnClickListener { startActivity(Intent(this@LoginActivity, HomeActivity::class.java)) }

    }
}