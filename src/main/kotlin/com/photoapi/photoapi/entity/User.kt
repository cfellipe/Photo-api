package com.photoapi.photoapi.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_user")
data class User(
        val name: String,
        @Column(unique = true)
        val email: String,
        override val createdDate: Date
) : BaseEntity(createdDate = createdDate)