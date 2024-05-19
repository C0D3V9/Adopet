package com.c0d3v9.adopet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.c0d3v9.adopet.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val animation = AnimationUtils.loadAnimation(this, R.anim.circle_explosion).apply {
            duration = 2500
            interpolator = AccelerateDecelerateInterpolator()
        }

        binding.fabcirlce!!.starAnimation(animation) {
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.firstpagecolor))
            binding.fabcirlce!!.isVisible = false
            binding.adopet!!.animate().translationY(1200F).setDuration(500).start()
            binding.youngwomen!!.animate().translationY(1200F).setDuration(500).start()
            binding.ellipse!!.animate().translationY(-1550F).setDuration(800).start()
            binding.startexp!!.animate().translationY(-1550F).setDuration(800).start()
            binding.connectinghearts!!.animate().translationY(-1550F).setDuration(800).start()
            binding.next!!.animate().translationY(-1550F).setDuration(800).start()


        }



       binding.next!!.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
