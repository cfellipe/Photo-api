package com.photoapi.photoapi.service

import com.photoapi.photoapi.config.exception.AppError
import com.photoapi.photoapi.config.exception.AppException
import com.photoapi.photoapi.config.security.TokenService
import com.photoapi.photoapi.entity.User
import com.photoapi.photoapi.entity.dto.LoginDTO
import com.photoapi.photoapi.entity.dto.TokenDTO
import com.photoapi.photoapi.entity.dto.UserRequestDTO
import com.photoapi.photoapi.entity.dto.UserResponseDTO
import com.photoapi.photoapi.entity.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
data class UserService(val userRepository: UserRepository,
                       val adminService: AdminService,
                       val authenticationManager: AuthenticationManager,
                       val tokenService: TokenService) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserService::class.java)
    }

    fun createUser(userRequest: UserRequestDTO): UserResponseDTO {

        val user = userRequest.convertToUser()
        val savedUser = userRepository.save(user)
        return UserResponseDTO.fromUser(savedUser)
    }

  /*  fun setProfile(profiles: MutableList<String>): MutableList<Profile> {
        val userProfiles = adminService.findAllProfiles()
        val profileSelected = userProfiles.minus(profiles.map { Profile(name = it) }).toMutableList()
        return profileSelected
    }*/


    fun updateUser(userDTO: UserRequestDTO, user: User) {
        updateFields(user, userDTO)
        userRepository.save(user)
    }

    fun deleteUser(user: User) {
        userRepository.delete(user)
    }

    fun findUserById(userId: Long): UserResponseDTO {
        val user = findUser(userId)
        return UserResponseDTO.fromUser(user)
    }

    fun userAuthentication(loginDTO: LoginDTO): TokenDTO {
        val loginFields = loginDTO.convertToUserAuthentication()

        try {
            val auth = authenticationManager.authenticate(loginFields)
            val token: String = tokenService.gerarToken(auth)
            return TokenDTO(token, "Bearer")
        } catch (e: AuthenticationException) {
            LOGGER.error("there was a error:", e.printStackTrace())
            throw AppException(AppError.INCORRECT_USER)
        }
    }

    fun findUser(userId: Long) = userRepository.findById(userId).orElseThrow { throw AppException(AppError.USER_NOT_FOUND) }

    private fun updateFields(user: User, userDTO: UserRequestDTO) {
        user.email = userDTO.email
        user.name = userDTO.name
    }
}