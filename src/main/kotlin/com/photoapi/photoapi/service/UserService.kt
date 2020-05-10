package com.photoapi.photoapi.service

import com.photoapi.photoapi.config.exception.AppError
import com.photoapi.photoapi.config.exception.AppException
import com.photoapi.photoapi.entity.Profile
import com.photoapi.photoapi.entity.User
import com.photoapi.photoapi.entity.dto.UserRequestDTO
import com.photoapi.photoapi.entity.dto.UserResponseDTO
import com.photoapi.photoapi.entity.repository.ProfileRepository
import com.photoapi.photoapi.entity.repository.UserRepository
import org.springframework.stereotype.Service

@Service
data class UserService(val userRepository: UserRepository,
                       val adminService: AdminService) {

    fun createUser(userRequest: UserRequestDTO): UserResponseDTO {

        val user = userRequest.convertToUser()
        val savedUser = userRepository.save(user)
        return UserResponseDTO.fromUser(savedUser)
    }

    fun setProfile(profiles: MutableList<String>): MutableList<Profile> {
        val userProfiles = adminService.findAllProfiles()
        val profileSelected = userProfiles.minus(profiles.map { Profile(name = it) }).toMutableList()
        return profileSelected
    }


    fun updateUser(userDTO: UserRequestDTO, userId: Long) {
        val user = findUser(userId)
        updateFields(user, userDTO)
        userRepository.save(user)
    }

    fun deleteUser(userId: Long) {
        val user = findUser(userId)
        userRepository.delete(user)
    }

    fun findUserById(userId: Long): UserResponseDTO {
        val user = findUser(userId)
        return UserResponseDTO.fromUser(user)
    }

    fun findUser(userId: Long) = userRepository.findById(userId).orElseThrow { throw AppException(AppError.USER_NOT_FOUND) }

    private fun updateFields(user: User, userDTO: UserRequestDTO) {
        user.email = userDTO.email
        user.name = userDTO.name
    }
}