package com.photoapi.photoapi.service

import com.photoapi.photoapi.config.exception.AppError
import com.photoapi.photoapi.config.exception.AppException
import com.photoapi.photoapi.entity.User
import com.photoapi.photoapi.entity.dto.UserDTO
import com.photoapi.photoapi.entity.repository.UserRepository
import org.springframework.stereotype.Service

@Service
data class UserService(val userRepository: UserRepository) {

    fun saveUser(user: UserDTO): UserDTO {
        val user = user.convertToUser()
        val savedUser = userRepository.save(user)
        return UserDTO.fromUser(savedUser)
    }

    fun updateUser(userDTO: UserDTO, userId: Long) {
        val user = findUser(userId)
        updateFields(user, userDTO)
        userRepository.save(user)
    }

    fun deleteUser(userId: Long) {
        val user = findUser(userId)
        userRepository.delete(user)
    }

    fun findUser(userId: Long) = userRepository.findById(userId).orElseThrow { throw AppException(AppError.USER_NOT_FOUND) }

    private fun updateFields(user: User, userDTO: UserDTO){
        user.email = userDTO.email
        user.name = userDTO.name
    }
}