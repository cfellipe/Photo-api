package com.photoapi.photoapi.entity

import java.util.*
import javax.persistence.ManyToOne

data class Post(
        val description: String,
        override val createdDate: Date,
        @ManyToOne
        val user: User
) : BaseEntity(createdDate = createdDate)