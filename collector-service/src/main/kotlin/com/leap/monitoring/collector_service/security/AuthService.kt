package com.leap.monitoring.collector_service.security

import com.leap.monitoring.collector_service.dto.LoginRequest
import com.leap.monitoring.collector_service.dto.LoginResponse
import com.leap.monitoring.collector_service.repository.secondary.UserAccountRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val repo: UserAccountRepository,
    private val encoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    fun login(request: LoginRequest): LoginResponse {

        val user = repo.findByUsername(request.username)
            ?: throw RuntimeException("User not found")

        // compare plain text password
        if (request.password != user.password) {
            throw RuntimeException("Invalid credentials")
        }

        val token = jwtService.generateToken(user.username)

        return LoginResponse(token)
    }
}
