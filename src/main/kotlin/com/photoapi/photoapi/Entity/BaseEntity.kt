package com.photoapi.photoapi.Entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@MappedSuperclass
open abstract class BaseEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        open val id: Long? = null,
        @Temporal(TemporalType.TIMESTAMP)
        open val createdDate: Date,
        @Temporal(TemporalType.TIMESTAMP)
        open val updateDate: Date = Date()

)
