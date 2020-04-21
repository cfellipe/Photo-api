package com.photoapi.photoapi.config.exception

class AppException(val appError: AppError) : RuntimeException(appError.message) {

    val status: Int = appError.status.value()
    val code: String = appError.code
}