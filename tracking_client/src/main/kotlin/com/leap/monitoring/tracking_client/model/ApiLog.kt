package com.leap.monitoring.tracking_client.model

data class ApiLog(
    val serviceName: String,
    val endpoint: String,
    val method: String,
    val status: Int,
    val requestSize: Long,
    val responseSize: Long,
    val latency: Long,
    val timestamp: Long = System.currentTimeMillis()
)
