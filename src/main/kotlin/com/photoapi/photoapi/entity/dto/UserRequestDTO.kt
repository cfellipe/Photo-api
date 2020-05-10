package com.photoapi.photoapi.entity.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.photoapi.photoapi.entity.Profile
import com.photoapi.photoapi.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.io.Serializable
import java.time.LocalDateTime


@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class UserRequestDTO(
        val name: String,
        val email: String,
        val password: String,
        val profiles: MutableList<String>
) : Serializable {

    fun convertToUser() = User(name = this.name,
            email = this.email,
            createdDate = LocalDateTime.now(),
            passwordUser = BCryptPasswordEncoder().encode(password))

}