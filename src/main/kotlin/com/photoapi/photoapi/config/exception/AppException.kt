package com.photoapi.photoapi.config.exception

class AppException(val error: AppError) : RuntimeException(error.message) {

    val status: Int = error.status.value()
    val code: String = error.code
}