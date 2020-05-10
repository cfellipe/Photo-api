package com.photoapi.photoapi.service

import com.photoapi.photoapi.config.exception.AppError
import com.photoapi.photoapi.config.exception.AppException
import com.photoapi.photoapi.entity.Profile
import com.photoapi.photoapi.entity.User
import com.photoapi.photoapi.entity.dto.ProfileDTO
import com.photoapi.photoapi.entity.dto.UserRequestDTO
import com.photoapi.photoapi.entity.dto.UserResponseDTO
import com.photoapi.photoapi.entity.repository.ProfileRepository
import com.photoapi.photoapi.entity.repository.UserRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
data class AdminService(val profileRepository: ProfileRepository) {

    @CacheEvict(cacheNames = ["Profile"], allEntries = true)
    fun createProfile(profileDTO: ProfileDTO): ProfileDTO {
        profileRepository.findProfileByName(profileDTO.name.toUpperCase())?.let { return ProfileDTO(name = it.name) }

        val savedProfile = profileRepository.save(Profile(name = profileDTO.name))
        return ProfileDTO(savedProfile.name)

    }

    @Cacheable(cacheNames = ["Profile"], key = "#root.method.name")
    fun findAllProfiles() = profileRepository.findAll()

}