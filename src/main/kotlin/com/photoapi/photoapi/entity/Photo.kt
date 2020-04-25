package com.photoapi.photoapi.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_photo")
data class Photo(
        override val id: Long,
        @Column(name = "web_path")
        val webPath: String,
        val name: String,
        val description: String,
        override val createdDate: Date,
        @ManyToOne
        val album: Album? = null
) : BaseEntity(createdDate = createdDate, id = id)