package com.leap.monitoring.collector_service.dto

data class IngestResponseDto(
    val success: Boolean,
    val savedCount: Int = 0,
    val message: String? = null
)
