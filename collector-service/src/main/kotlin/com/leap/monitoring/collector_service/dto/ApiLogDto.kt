package com.leap.monitoring.collector_service.dto

import java.time.Instant

data class ApiLogDto(
    val serviceName: String,
    val endpoint: String,
    val method: String = "GET",
    val statusCode: Int,
    val latencyMs: Long,
    val requestHeaders: Map<String, String>? = null,
    val requestBody: String? = null,
    val responseHeaders: Map<String, String>? = null,
    val responseBody: String? = null,
    val timestamp: Long
)
