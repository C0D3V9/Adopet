package com.c0d3v9.adopet.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.c0d3v9.adopet.home.EXTRA_POST_ID
import com.c0d3v9.adopet.home.HomeActivity
import com.c0d3v9.adopet.databinding.ActivityDetailsBinding
const val EXTRA_POST = "EXTRA_POST"
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        val postId = intent.getIntExtra(EXTRA_POST_ID,-1)

        viewModel.post.observe(this, Observer {
                Post ->
            binding.petAgeDetail.text = "Post ${Post.id}"
            binding.petOwnerDetail.text = Post.title
            binding.petDescDetail.text = Post.body
        })

        viewModel.user.observe(this, Observer {
                user ->
            binding.petOwnerLocationDetail.text = user.name
            binding.petOwnerNumberDetail.text = user.email
            binding.petAgeDetail.text = user.username
            binding.petWeightDetail.text = user.website
        })

        viewModel.getPostDetails(postId)

        binding.backButton.setOnClickListener { startActivity(Intent(this@DetailsActivity, HomeActivity::class.java)) }
    }
}