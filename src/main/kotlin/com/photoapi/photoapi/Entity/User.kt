package com.photoapi.photoapi.Entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_user")
data class User(
        override val id: Long,
        val name: String,
        @Column(unique = true)
        val email: String,
        override val createdDate: Date
) : BaseEntity(id, createdDate)