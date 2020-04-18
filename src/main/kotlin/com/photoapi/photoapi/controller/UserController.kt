package com.photoapi.photoapi.controller

import com.photoapi.photoapi.entity.dto.UserDTO
import com.photoapi.photoapi.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
data class UserController(val userService: UserService) {

    @PostMapping()
    fun createUser(@RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.saveUser(user))
    }

}