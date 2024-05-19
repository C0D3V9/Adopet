package com.c0d3v9.adopet.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

object RetrofitInstance {

    private val retrofit by lazy{

        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    val api: PostApi by lazy {
        retrofit.create(PostApi::class.java)
    }

}