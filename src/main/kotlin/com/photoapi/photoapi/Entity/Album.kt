package com.photoapi.photoapi.Entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_album")
data class Album(
        override val id: Long,
        @ManyToOne
        val user: User,
        override val createdDate: Date
) : BaseEntity(id, createdDate)