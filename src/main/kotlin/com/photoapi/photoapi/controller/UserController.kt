package com.photoapi.photoapi.controller

import com.photoapi.photoapi.entity.dto.UserDTO
import com.photoapi.photoapi.service.UserService
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
data class UserController(val userService: UserService) {

    @ApiOperation(value = "Create new user")
    @PostMapping()
    fun createUser(@RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.saveUser(user))
    }

    @ApiOperation(value = "Create new user")
    @PutMapping("/{userId}")
    fun updateUser(@PathVariable userId: Long, @RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.saveUser(user))
    }

}