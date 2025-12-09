package com.leap.monitoring.collector_service.model.metadata

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "alerts")
data class AlertHistory(
    @Id
    val id: String? = null,

    val serviceName: String,
    val endpoint: String,

    val alertType: String,     // HIGH_LATENCY / SERVER_ERROR / RATE_LIMIT_HIT / HIGH_ERROR_RATE / SERVICE_DOWN
    val message: String,

    val timestamp: Instant = Instant.now(),

    // new fields for issue resolution workflow
    val resolved: Boolean = false,
    val resolvedAt: Instant? = null,
    val resolvedBy: String? = null
)
