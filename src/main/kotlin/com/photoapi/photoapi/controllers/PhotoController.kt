package com.photoapi.photoapi.controllers

import com.photoapi.photoapi.entity.User
import com.photoapi.photoapi.service.PhotoService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping("/photo")
data class PhotoController(val photoService: PhotoService) {

    @ApiOperation(value = "Upload a image")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
    @PostMapping("/upload")
    fun uploadImage(@RequestPart(value = "file") file: MultipartFile, auth: Authentication): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.CREATED).body(photoService.savePhoto(file, auth.principal as User))
    }


}