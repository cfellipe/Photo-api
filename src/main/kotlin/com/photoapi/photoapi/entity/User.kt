package com.photoapi.photoapi.entity

import com.photoapi.photoapi.entity.dto.UserDTO
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_user")
data class User(
        override val id: Long?,
        var name: String,
        @Column(unique = true)
        var email: String,
        override val createdDate: Date
) : BaseEntity(createdDate = createdDate, id = id)