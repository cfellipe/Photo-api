package com.photoapi.photoapi.config.exception

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@Controller
@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = [AppException::class])
    fun appException(exception: AppException): ResponseEntity<*> {
        val body = HashMap<String, String>()
        body.put("error", exception.code)
        body.put("errorDescription", exception.message.toString())
        return ResponseEntity.status(exception.status).body(body)
    }
}