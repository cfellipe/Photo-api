package com.photoapi.photoapi.entity.dto

import com.photoapi.photoapi.entity.Post
import com.photoapi.photoapi.entity.User
import java.util.*

data class PostDTO(
        val description: String,
        val userId: Long
) {
    fun convertToPost(user: User) = Post(description = this.description, createdDate = Date(), user = user, id = null)

    companion object {
        fun fromPost(post: Post) = PostDTO(description = post.description, userId = post.user.id!!)
    }
}