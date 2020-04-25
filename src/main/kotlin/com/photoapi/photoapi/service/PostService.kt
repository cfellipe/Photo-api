package com.photoapi.photoapi.service

import com.photoapi.photoapi.config.exception.AppError
import com.photoapi.photoapi.config.exception.AppException
import com.photoapi.photoapi.entity.Post
import com.photoapi.photoapi.entity.dto.PostDTO
import com.photoapi.photoapi.entity.repository.PostRepository
import com.photoapi.photoapi.entity.repository.UserRepository
import org.springframework.stereotype.Service

@Service
data class PostService(val postRepository: PostRepository,
                       val userService: UserService) {

    fun createPost(postDTO: PostDTO): PostDTO {
        val user = userService.findUser(postDTO.userId)
        val post = postDTO.convertToPost(user)
        val savedPost = postRepository.save(post)
        return PostDTO.fromPost(savedPost)
    }

    fun updatePost(postDTO: PostDTO, postId: Long) {
        val post = findPost(postId)
        updateFields(post, postDTO)
        postRepository.save(post)
    }

    fun deletePost(postId: Long) {
        val post = findPost(postId)
        postRepository.delete(post)
    }

    private fun updateFields(post: Post, postDTO: PostDTO) {
        post.description = postDTO.description
    }

    private fun findPost(postId: Long) = postRepository.findById(postId).orElseThrow { throw AppException(AppError.POST_NOT_FOUND) }

}



