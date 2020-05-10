package com.photoapi.photoapi.controllers

import com.photoapi.photoapi.entity.dto.PostDTO
import com.photoapi.photoapi.service.PostService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/post")
data class PostController(val postService: PostService) {


    @ApiOperation(value = "Create post of user")
    @PostMapping()
    fun createPost(@RequestBody postDTO: PostDTO): ResponseEntity<PostDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postDTO))
    }

    @ApiOperation(value = "Update post")
    @PutMapping("/{postId}")
    fun updatePost(@PathVariable postId: Long, @RequestBody postDTO: PostDTO): ResponseEntity<Void> {
        postService.updatePost(postDTO, postId)
        return ResponseEntity.noContent().build()
    }

    @ApiOperation("Delete Post")
    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Void> {
        postService.deletePost(postId)
        return ResponseEntity.noContent().build()
    }

}