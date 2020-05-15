package com.photoapi.photoapi.controllers

import com.photoapi.photoapi.entity.dto.LoginDTO
import com.photoapi.photoapi.service.AuthenticationService
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/auth")
data class AuthenticationController(val authenticationService: AuthenticationService) {

    @ApiOperation(value = "Authenticate a user")
    @PostMapping
    fun authenticate(@RequestBody loginDTO: LoginDTO): ResponseEntity<*> {
        return ResponseEntity.ok().body(authenticationService.userAuthentication(loginDTO))
    }


}