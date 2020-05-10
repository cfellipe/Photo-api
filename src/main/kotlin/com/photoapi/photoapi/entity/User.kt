package com.photoapi.photoapi.entity


import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_user")
data class User(
        var name: String,
        @Column(name = "password") val passwordUser: String?,
        @Column(unique = true) var email: String,
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "rl_user_profile", joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "profile_id")])
        var profile: MutableList<Profile> = mutableListOf(Profile(name = "User")),
        override val createdDate: LocalDateTime
) : BaseEntity(createdDate = createdDate), UserDetails {

    override fun getAuthorities() = this.profile

    override fun isEnabled() = true

    override fun getUsername() = this.email

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = this.passwordUser

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}