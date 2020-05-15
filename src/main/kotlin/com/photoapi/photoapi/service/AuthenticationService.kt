package com.photoapi.photoapi.service

import com.photoapi.photoapi.config.exception.AppError
import com.photoapi.photoapi.config.exception.AppException
import com.photoapi.photoapi.config.security.TokenService
import com.photoapi.photoapi.entity.dto.LoginDTO
import com.photoapi.photoapi.entity.dto.TokenDTO
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException


@Service
data class AuthenticationService(val authManager: AuthenticationManager, val tokenService: TokenService) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AuthenticationService::class.java)
    }

    fun userAuthentication(loginDTO: LoginDTO): TokenDTO {
        val loginFields = loginDTO.convertToUserAuthentication()

        try {
            val auth = authManager.authenticate(loginFields)
            val token: String = tokenService.gerarToken(auth)
            return TokenDTO(token, "Bearer")
        } catch (e: AuthenticationException) {
            LOGGER.error("there was a error:", e.printStackTrace())
            throw AppException(AppError.INCORRECT_USER)
        }
    }
}