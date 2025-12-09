package com.leap.monitoring.collector_service.repository.secondary

import com.leap.monitoring.collector_service.model.metadata.RateLimitOverride
import org.springframework.data.mongodb.repository.MongoRepository

interface RateLimitOverrideRepository : MongoRepository<RateLimitOverride, String> {

    fun findByServiceName(serviceName: String): RateLimitOverride?
}
