package com.photoapi.photoapi.entity.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.photoapi.photoapi.entity.User
import java.io.Serializable
import java.time.LocalDateTime


@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class UserResponseDTO(
        val id: Long? = null,
        val name: String,
        val email: String
) : Serializable {

    companion object {
        fun fromUser(user: User) = UserResponseDTO(user.id, user.name, user.email)
    }

}