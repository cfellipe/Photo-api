package com.photoapi.photoapi.entity

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "tb_photo")
data class Photo(
        @Column(name = "web_path")
        val webPath: String,
        val name: String? = null,
        val description: String? = null,
        @ManyToOne
        val album: Album? = null,
        override val createdDate: LocalDateTime
) : BaseEntity(createdDate = createdDate)