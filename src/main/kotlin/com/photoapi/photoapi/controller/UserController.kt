package com.photoapi.photoapi.controller

import com.photoapi.photoapi.entity.dto.UserDTO
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
    fun createUser(@RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user))
    }

    @ApiOperation(value = "Update user")
    @PutMapping("/{userId}")
    fun updateUser(@PathVariable userId: Long, @RequestBody userDTO: UserDTO): ResponseEntity<Void> {
        userService.updateUser(userDTO, userId)
        return ResponseEntity.noContent().build()
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Long): ResponseEntity<Void> {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build()
    }

}