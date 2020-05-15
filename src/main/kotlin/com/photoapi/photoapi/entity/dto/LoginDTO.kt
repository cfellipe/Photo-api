package com.photoapi.photoapi.entity.dto

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

data class LoginDTO(val email: String,
                    val password: String) {


    fun convertToUserAuthentication() = UsernamePasswordAuthenticationToken(this.email, this.password)
}