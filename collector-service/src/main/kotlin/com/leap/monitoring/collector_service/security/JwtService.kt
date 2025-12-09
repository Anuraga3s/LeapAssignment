package com.leap.monitoring.collector_service.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(
    @Value("\${jwt.secret}") secret: String,
    @Value("\${jwt.expiration-ms}") private val expirationMs: Long
) {

    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(username: String): String {
        val now = Date()
        val expiry = Date(now.time + expirationMs)

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun extractUsername(token: String): String? =
        extractAllClaims(token).subject

    fun isTokenValid(token: String): Boolean =
        try {
            extractAllClaims(token)
            true
        } catch (e: Exception) {
            false
        }

    private fun extractAllClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
}
