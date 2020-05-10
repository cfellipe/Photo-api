package com.photoapi.photoapi.entity.repository

import com.photoapi.photoapi.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface ProfileRepository : JpaRepository<Profile, Long> {

    fun findProfileByName(name: String): Profile?
}