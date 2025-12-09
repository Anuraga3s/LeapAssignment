package com.leap.monitoring.collector_service.model.logs

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "rate_limit_hits")
data class RateLimitHitLog(

    @Id
    val id: String? = null,

    val serviceName: String,
    val endpoint: String? = null,

    val limit: Int,
    val hitAt: Instant = Instant.now()
)
