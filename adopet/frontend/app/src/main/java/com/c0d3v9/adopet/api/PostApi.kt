package com.c0d3v9.adopet.api

import com.c0d3v9.adopet.model.Post
import com.c0d3v9.adopet.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId:Int):Post

    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId:Int): User

    @POST("posts")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>
}