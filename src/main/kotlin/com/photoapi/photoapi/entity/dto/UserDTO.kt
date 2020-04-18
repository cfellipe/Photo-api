package com.photoapi.photoapi.entity.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.photoapi.photoapi.entity.User
import java.util.*

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class UserDTO(
        val id: Long? = null,
        val name: String,
        val email: String
) {
    fun convertToUser() = User(name = this.name, email = this.email, createdDate = Date())

    companion object {
        fun fromUser(user: User) = UserDTO(user.id, user.name, user.email)
    }
}