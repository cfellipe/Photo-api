package com.photoapi.photoapi.controllers

import com.photoapi.photoapi.entity.dto.ProfileDTO
import com.photoapi.photoapi.service.AdminService
import com.photoapi.photoapi.service.PhotoService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping("/admin")
data class AdminController(val adminService: AdminService) {

    @ApiOperation(value = "Upload a image")
    @PostMapping("/profile")
    fun createProfile(@RequestBody profileDTO: ProfileDTO): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createProfile(profileDTO))
    }


}