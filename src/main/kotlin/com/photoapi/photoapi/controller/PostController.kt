package com.photoapi.photoapi.controller

import com.photoapi.photoapi.service.PostService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/post")
data class PostController(val postService: PostService){


    fun createPost()

}