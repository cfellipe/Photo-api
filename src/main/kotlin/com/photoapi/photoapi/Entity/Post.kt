package com.photoapi.photoapi.Entity

import org.springframework.data.annotation.CreatedDate
import java.util.*
import javax.persistence.ManyToOne

data class Post(
        override val id: Long,
        val description: String,
        override val createdDate: Date,
        @ManyToOne
        val user: User
) : BaseEntity(id, createdDate)