package com.leap.monitoring.collector_service.dto

data class LogSearchRequest(
    val serviceName: String? = null,
    val endpoint: String? = null,
    val statusCode: Int? = null,
    val fromTime: Long? = null,
    val toTime: Long? = null,
    val limit: Int = 50
)
