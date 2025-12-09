package com.leap.monitoring.collector_service.model.logs

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.index.Indexed
import java.time.Instant

@Document(collection = "api_logs")
data class ApiLog(

    @Id
    val id: String? = null,

    @Indexed
    val serviceName: String,

    @Indexed
    val endpoint: String,

    val method: String,
    val statusCode: Int,
    val latencyMs: Long,

    val requestHeaders: Map<String, String>? = null,
    val requestBody: String? = null,

    val responseHeaders: Map<String, String>? = null,
    val responseBody: String? = null,

    val timestamp: Instant = Instant.now()
)
