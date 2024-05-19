package com.c0d3v9.adopet.model

import java.io.Serializable

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
): Serializable