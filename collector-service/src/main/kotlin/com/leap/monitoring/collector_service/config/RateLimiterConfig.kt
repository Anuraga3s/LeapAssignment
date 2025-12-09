package com.leap.monitoring.collector_service.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class RateLimiterConfig(

    @Value("\${rateLimiter.defaultLimitPerSecond}")
    val defaultLimitPerSecond: Int
)
