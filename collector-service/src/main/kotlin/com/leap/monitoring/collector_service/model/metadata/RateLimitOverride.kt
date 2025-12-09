package com.leap.monitoring.collector_service.model.metadata

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "rate_limit_overrides")
data class RateLimitOverride(

    @Id
    val id: String? = null,

    val serviceName: String,
    val newLimit: Int,
    val enabled: Boolean = true
)
