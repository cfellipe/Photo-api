package com.photoapi.photoapi.controllers

import com.photoapi.photoapi.entity.User
import com.photoapi.photoapi.entity.dto.LoginDTO
import com.photoapi.photoapi.entity.dto.UserRequestDTO
import com.photoapi.photoapi.entity.dto.UserResponseDTO
import com.photoapi.photoapi.service.UserService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
data class UserController(val userService: UserService) {

    @ApiOperation(value = "Create new user")
    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequestDTO): ResponseEntity<UserResponseDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest))
    }

    @ApiOperation(value = "Update user")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
    @PutMapping
    fun updateUser(@RequestBody userDTO: UserRequestDTO, auth: Authentication): ResponseEntity<Void> {
        userService.updateUser(userDTO, auth.principal as User)
        return ResponseEntity.noContent().build()
    }

    @ApiOperation(value = "Delete user")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", example = "Bearer access_token")
    @DeleteMapping
    fun deleteUser(auth: Authentication): ResponseEntity<Void> {
        userService.deleteUser(auth.principal as User)
        return ResponseEntity.noContent().build()
    }


    @ApiOperation(value = "Authenticate a user")
    @PostMapping("/auth")
    fun authenticate(@RequestBody loginDTO: LoginDTO): ResponseEntity<*> {
        return ResponseEntity.ok().body(userService.userAuthentication(loginDTO))
    }

}