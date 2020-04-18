package com.photoapi.photoapi.entity

import java.util.*
import javax.persistence.*

@MappedSuperclass
open abstract class BaseEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        open val id: Long? = null,
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_date")
        open val createdDate: Date,
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "updated_date")
        open val updateDate: Date = Date()

)
