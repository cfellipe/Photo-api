package com.photoapi.photoapi.config.security

import com.photoapi.photoapi.config.exception.AppError
import com.photoapi.photoapi.config.exception.AppException
import com.photoapi.photoapi.entity.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
data class AuthenticationService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(userEmail: String) = userRepository.findByEmail(userEmail)
            ?: throw AppException(AppError.USER_NOT_FOUND)
}

