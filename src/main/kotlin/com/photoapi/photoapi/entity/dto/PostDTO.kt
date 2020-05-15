package com.photoapi.photoapi.entity.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.photoapi.photoapi.entity.Post
import com.photoapi.photoapi.entity.User
import java.time.LocalDateTime


@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class PostDTO(
        val description: String
) {
    fun convertToPost(user: User) = Post(description = this.description, createdDate = LocalDateTime.now(), user = user)

    companion object {
        fun fromPost(post: Post) = PostDTO(description = post.description)
    }
}