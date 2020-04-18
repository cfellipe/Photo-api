package com.photoapi.photoapi.service

import com.photoapi.photoapi.entity.dto.UserDTO
import com.photoapi.photoapi.entity.repository.UserRepository
import org.springframework.stereotype.Service

@Service
data class UserService(val userRepository: UserRepository){

    fun saveUser(user: UserDTO): UserDTO {
        val user = user.convertToUser()
        var savedUser = userRepository.save(user)
        return UserDTO.fromUser(savedUser)

    }

}