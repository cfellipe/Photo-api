package com.photoapi.photoapi.config.security

import com.photoapi.photoapi.entity.repository.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


data class AuthenticationFilter(val tokenService: TokenService, val userRepository: UserRepository) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token: String? = getToken(request)
        if (tokenService.isTokenValido(token)) {
            authenticateUser(token!!)
        }
        filterChain.doFilter(request, response)
    }

    private fun authenticateUser(token: String) {
        val idUsuario = tokenService.getIdUsuario(token)
        val usuario = userRepository.findById(idUsuario).get()
        val autentication = UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities())
        SecurityContextHolder.getContext().authentication = autentication
    }

    private fun getToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        return if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            null
        } else token.substring(7, token.length)
    }

}

