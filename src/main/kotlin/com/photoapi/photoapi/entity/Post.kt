package com.photoapi.photoapi.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_post")
data class Post(
        var description: String,
        override val createdDate: Date,
        @ManyToOne
        val user: User
) : BaseEntity(createdDate = createdDate)