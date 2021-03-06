package com.photoapi.photoapi.config.exception

import org.springframework.http.HttpStatus

enum class AppError(val status: HttpStatus, val code: String, val message: String) {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"API_001", "User not found"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND,"API_002", "Post not found"),

    UPLOAD_FILE_ERROR(HttpStatus.BAD_REQUEST,"API_101", "There was an error with the uploading file"),
    INCORRECT_USER(HttpStatus.BAD_REQUEST,"API_102", "Incorrect user or password"),

}