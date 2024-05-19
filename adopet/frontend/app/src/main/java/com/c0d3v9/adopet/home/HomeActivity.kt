package com.c0d3v9.adopet.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c0d3v9.adopet.databinding.ActivityHomeBinding
import com.c0d3v9.adopet.model.Post
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.c0d3v9.adopet.ProfileActivity
import com.c0d3v9.adopet.add.AddActivity
import com.c0d3v9.adopet.details.DetailsActivity


const val EXTRA_POST_ID = "EXTRA_POST_ID"

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var postAdapter: PostAdapter
    private val blogPosts = mutableListOf<Post>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.posts.observe(this, Observer {
                posts ->
            Log.i(TAG,"Number of Posts are : ${posts.size}")
            blogPosts.addAll(posts)
            postAdapter.notifyDataSetChanged()
        })
        //  postAdapter =  PostAdapter(this,blogPosts)

        postAdapter =  PostAdapter(this,blogPosts, object : PostAdapter.ItemClickListener {
            override fun onItemClick(post: Post) {
                val intent = Intent(this@HomeActivity, DetailsActivity::class.java)
                intent.putExtra(EXTRA_POST_ID, post.id)
                startActivity(intent)
            }

        })

        binding.posts.adapter = postAdapter
        binding.posts.layoutManager = LinearLayoutManager(this)
        viewModel.getPosts()


        binding.addHome.setOnClickListener { startActivity(Intent(this@HomeActivity, AddActivity::class.java)) }
        binding.profileNotSelectedHome.setOnClickListener { startActivity(Intent(this@HomeActivity, ProfileActivity::class.java)) }


    }

}
