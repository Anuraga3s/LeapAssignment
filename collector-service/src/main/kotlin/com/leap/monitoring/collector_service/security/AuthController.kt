package com.leap.monitoring.collector_service.security

import com.leap.monitoring.collector_service.dto.LoginRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest) =
        authService.login(request)
}
