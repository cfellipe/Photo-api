package com.photoapi.photoapi.entity

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_album")
data class Album(
        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User,
        override val createdDate: LocalDateTime
) : BaseEntity(createdDate = createdDate)