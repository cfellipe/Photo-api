package com.photoapi.photoapi.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
@Configuration
data class SecurityConfiguration(val securityService: TokenService) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(securityService).passwordEncoder(BCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers(GET, "/actuator").permitAll()
                .antMatchers(GET, "/actuator/**").permitAll()
                .antMatchers(POST, "/user").permitAll()
                .antMatchers(POST,"/auth").permitAll()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    override fun configure(web: WebSecurity) {
    }
}