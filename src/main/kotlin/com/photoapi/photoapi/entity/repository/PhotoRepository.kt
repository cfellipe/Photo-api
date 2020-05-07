package com.photoapi.photoapi.entity.repository

import com.photoapi.photoapi.entity.Photo
import com.photoapi.photoapi.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PhotoRepository : JpaRepository<Photo, Long>