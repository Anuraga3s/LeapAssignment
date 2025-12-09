package com.leap.monitoring.collector_service.dto

import java.time.Instant

data class RateLimitHitDto(
    val serviceName: String,
    val endpoint: String? = null,
    val limit: Int,
    val hitAt: Instant? = null
)
