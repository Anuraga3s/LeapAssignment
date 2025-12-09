package com.leap.monitoring.collector_service.repository.primary

import com.leap.monitoring.collector_service.model.logs.RateLimitHitLog
import org.springframework.data.mongodb.repository.MongoRepository

interface RateLimitHitLogRepository : MongoRepository<RateLimitHitLog, String> {

    fun findByServiceName(serviceName: String): List<RateLimitHitLog>
}
