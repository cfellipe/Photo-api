package com.photoapi.photoapi.Entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_photo")
data class Photo(
        override val id: Long,
        val webPath: String,
        val name: String,
        val description: String,
        override val createdDate: Date,
        @ManyToOne
        val album: Album? = null
) : BaseEntity(id, createdDate)