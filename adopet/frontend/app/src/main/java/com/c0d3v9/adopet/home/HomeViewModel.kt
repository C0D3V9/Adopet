package com.c0d3v9.adopet.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c0d3v9.adopet.api.RetrofitInstance
import com.c0d3v9.adopet.model.Post
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>>
        get() = _posts

    fun getPosts() {
        viewModelScope.launch {
            val fetchedPosts = RetrofitInstance.api.getPosts()
            Log.i(TAG, "Your posts: $fetchedPosts")
            _posts.value = fetchedPosts
        }
    }

}