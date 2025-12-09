package com.leap.monitoring.collector_service.service

import com.leap.monitoring.collector_service.repository.primary.ApiLogRepository
import com.leap.monitoring.collector_service.repository.primary.RateLimitHitLogRepository
import com.leap.monitoring.collector_service.repository.secondary.AlertHistoryRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class StatsService(
    private val apiRepo: ApiLogRepository,
    private val hitRepo: RateLimitHitLogRepository,
    private val alertRepo: AlertHistoryRepository
) {
    fun generateStats(): Map<String, Any> {
        val logs = apiRepo.findAll()
        if (logs.isEmpty()) return emptyMap()

        val now = Instant.now().toEpochMilli()
        val oneMinuteAgo = now - 60_000

        val rpm = logs.count { it.timestamp >= oneMinuteAgo }

        val total = logs.size
        val errors = logs.count { it.statusCode in 500..599 }
        val errorRate = if (total > 0) (errors * 100.0 / total) else 0.0

        val avgLatency = logs.map { it.latencyMs }.average()

        val topEndpoints = logs
            .groupBy { it.endpoint }
            .mapValues { it.value.size }
            .entries
            .sortedByDescending { it.value }
            .take(5)
            .associate { it.key to it.value }

        val rateLimitHits = hitRepo.findAll().size

        return mapOf(
            "requestsPerMinute" to rpm,
            "errorRatePercent" to errorRate,
            "averageLatencyMs" to avgLatency,
            "topEndpoints" to topEndpoints,
            "rateLimitHits" to rateLimitHits,
            "totalAlerts" to alertRepo.count()
        )
    }


}
