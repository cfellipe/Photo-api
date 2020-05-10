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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as com.photoapi.photoapi.entity.Profile

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}