package com.photoapi.photoapi.controllers

import com.photoapi.photoapi.entity.dto.UserRequestDTO
import com.photoapi.photoapi.entity.dto.UserResponseDTO
import com.photoapi.photoapi.service.UserService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
data class UserController(val userService: UserService) {

    @ApiOperation(value = "Create new user")
    @PostMapping()
    fun createUser(@RequestBody user: UserRequestDTO): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user))
    }

    @ApiOperation(value = "Update user")
    @PutMapping("/{userId}")
    fun updateUser(@PathVariable userId: Long, @RequestBody userDTO: UserRequestDTO): ResponseEntity<Void> {
        userService.updateUser(userDTO, userId)
        return ResponseEntity.noContent().build()
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Long): ResponseEntity<Void> {
        userService.deleteUser(userId)
        return ResponseEntity.noContent().build()
    }

    @ApiOperation(value = "Find user by id")
    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Long): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(userId))
    }

}