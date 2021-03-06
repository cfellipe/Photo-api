package com.photoapi.photoapi.config.security

import com.photoapi.photoapi.config.exception.AppError
import com.photoapi.photoapi.config.exception.AppException
import com.photoapi.photoapi.entity.User
import com.photoapi.photoapi.entity.repository.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
data class TokenService(val userRepository: UserRepository,
                        @Value("\${config.jwt.expiration}")
                        val expiration: String,
                        @Value("\${config.jwt.secret}")
                        val secret: String) : UserDetailsService {

    override fun loadUserByUsername(userEmail: String) = userRepository.findByEmail(userEmail)
            ?: throw AppException(AppError.USER_NOT_FOUND)

    fun generateToken(auth: Authentication): String {
        val user = auth.getPrincipal() as User
        return Jwts.builder()
                .setIssuer("Photo Api")
                .setSubject(user.id.toString())
                .setIssuedAt(Date())
                .setExpiration(Date(Date().getTime() + expiration.toLong()))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret)
                .compact()
    }

    fun isValidToken(token: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun getIdUsuario(token: String?): Long {
        val claims: Claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody()
        return claims.getSubject().toLong()
    }
}

