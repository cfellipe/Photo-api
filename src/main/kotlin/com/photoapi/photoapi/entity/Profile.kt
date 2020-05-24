package com.photoapi.photoapi.entity

import org.springframework.context.annotation.Profile
import org.springframework.security.core.GrantedAuthority
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_profile")
data class Profile(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val name: String
) : GrantedAuthority {

    override fun getAuthority() = this.name
    
}