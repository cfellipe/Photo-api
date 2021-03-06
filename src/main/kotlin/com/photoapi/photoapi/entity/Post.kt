package com.photoapi.photoapi.entity

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_post")
data class Post(
        var description: String,
        @ManyToOne
        val user: User,
        override val createdDate: LocalDateTime
) : BaseEntity(createdDate = createdDate)