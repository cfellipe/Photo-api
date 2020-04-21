package com.photoapi.photoapi.config.exception

import org.springframework.http.HttpStatus

enum class AppError(val status: HttpStatus, val code: String, val message: String) {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"Api001", "User not found")

}