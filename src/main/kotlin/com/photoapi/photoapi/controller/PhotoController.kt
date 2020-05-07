package com.photoapi.photoapi.controller

import com.photoapi.photoapi.entity.dto.PhotoDTO
import com.photoapi.photoapi.entity.dto.PostDTO
import com.photoapi.photoapi.entity.dto.UserDTO
import com.photoapi.photoapi.service.PhotoService
import com.photoapi.photoapi.service.PostService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping("/photo")
data class PhotoController(val photoService: PhotoService) {

    @ApiOperation(value = "Upload a image")
    @PostMapping("/upload")
    fun uploadImage(@RequestPart(value = "file") file: MultipartFile): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.CREATED).body(photoService.savePhoto(file))
    }


}