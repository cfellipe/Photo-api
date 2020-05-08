package com.photoapi.photoapi.entity

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        open val id: Long? = null,
        @Column(name = "created_date")
        open val createdDate: LocalDateTime,
        @Column(name = "updated_date")
        open val updateDate: LocalDateTime = LocalDateTime.now()

)
