package com.c0d3v9.adopet.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c0d3v9.adopet.api.RetrofitInstance
import com.c0d3v9.adopet.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class AddViewModel:ViewModel() {

    val _post: MutableLiveData<Response<Post>> = MutableLiveData()
    val post: LiveData<Response<Post>> get() = _post

    fun pushPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.pushPost(post)
                _post.postValue(response)
                if (response.isSuccessful) {
                    Log.d("AddViewModel", "Post request successful: ${response.body()}")
                } else {
                    Log.e(
                        "AddViewModel",
                        "Post request failed: ${response.code()} ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("AddViewModel", "Post request error: ${e.message}", e)
            }
        }

    }
}