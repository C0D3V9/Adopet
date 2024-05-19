package com.c0d3v9.adopet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c0d3v9.adopet.add.AddActivity
import com.c0d3v9.adopet.databinding.ActivityProfileBinding
import com.c0d3v9.adopet.home.HomeActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.logoutProfile.setOnClickListener { startActivity(Intent(this@ProfileActivity, LoginActivity::class.java)) }
        binding.addProfile.setOnClickListener { startActivity(Intent(this@ProfileActivity, AddActivity::class.java)) }
        binding.homeNotSelectedProfile.setOnClickListener { startActivity(Intent(this@ProfileActivity, HomeActivity::class.java)) }


    }
}