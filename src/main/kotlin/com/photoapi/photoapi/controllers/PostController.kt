package com.photoapi.photoapi.controllers

import com.photoapi.photoapi.entity.User
import com.photoapi.photoapi.entity.dto.PostDTO
import com.photoapi.photoapi.service.PostService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/post")
data class PostController(val postService: PostService) {


    @ApiOperation(value = "Create post of user")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
    @PostMapping()
    fun createPost(@RequestBody postDTO: PostDTO, auth: Authentication): ResponseEntity<PostDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postDTO, auth.principal as User))
    }

    @ApiOperation(value = "Update post")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
    @PutMapping("/{postId}")
    fun updatePost(@PathVariable postId: Long, @RequestBody postDTO: PostDTO, auth: Authentication): ResponseEntity<Void> {
        postService.updatePost(postDTO, postId, auth.principal as User)
        return ResponseEntity.noContent().build()
    }

    @ApiOperation("Delete Post")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long, auth: Authentication): ResponseEntity<Void> {
        postService.deletePost(postId, auth.principal as User)
        return ResponseEntity.noContent().build()
    }

}