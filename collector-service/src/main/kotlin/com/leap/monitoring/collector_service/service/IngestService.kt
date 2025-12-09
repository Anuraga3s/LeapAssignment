package com.leap.monitoring.collector_service.service

import com.leap.monitoring.collector_service.dto.ApiLogDto
import com.leap.monitoring.collector_service.dto.IngestResponseDto
import com.leap.monitoring.collector_service.dto.RateLimitHitDto
import com.leap.monitoring.collector_service.model.logs.ApiLog
import com.leap.monitoring.collector_service.model.logs.RateLimitHitLog
import com.leap.monitoring.collector_service.repository.primary.ApiLogRepository
import com.leap.monitoring.collector_service.repository.primary.RateLimitHitLogRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class IngestService(
    private val apiLogRepo: ApiLogRepository,
    private val rateLimitRepo: RateLimitHitLogRepository,
    private val alertService: AlertService
) {

    /**
     * Save a SINGLE Api Log
     */
    fun saveApiLog(dto: ApiLogDto): IngestResponseDto {

        val log = ApiLog(
            serviceName = dto.serviceName,
            endpoint = dto.endpoint,
            method = dto.method,
            statusCode = dto.statusCode,
            latencyMs = dto.latencyMs,
            requestHeaders = dto.requestHeaders,
            requestBody = dto.requestBody,
            responseHeaders = dto.responseHeaders,
            responseBody = dto.responseBody,
            timestamp = dto.timestamp ?: Instant.now()
        )

        apiLogRepo.save(log)

        // Trigger alert engine
        alertService.processApiLog(log)

        return IngestResponseDto(success = true, savedCount = 1)
    }

    /**
     * Save MULTIPLE Api Logs (bulk ingestion)
     */
    fun saveApiLogs(list: List<ApiLogDto>): IngestResponseDto {

        val logs = list.map { dto ->
            ApiLog(
                serviceName = dto.serviceName,
                endpoint = dto.endpoint,
                method = dto.method,
                statusCode = dto.statusCode,
                latencyMs = dto.latencyMs,
                requestHeaders = dto.requestHeaders,
                requestBody = dto.requestBody,
                responseHeaders = dto.responseHeaders,
                responseBody = dto.responseBody,
                timestamp = dto.timestamp ?: Instant.now()
            )
        }

        apiLogRepo.saveAll(logs)

        logs.forEach { alertService.processApiLog(it) }

        return IngestResponseDto(success = true, savedCount = logs.size)
    }

    /**
     * Save Rate Limit Hit Events
     */
    fun saveRateLimitHit(dto: RateLimitHitDto): IngestResponseDto {

        val hit = RateLimitHitLog(
            serviceName = dto.serviceName,
            endpoint = dto.endpoint,
            limit = dto.limit,
            hitAt = dto.hitAt ?: Instant.now()
        )

        rateLimitRepo.save(hit)

        // Trigger alert engine for RATE_LIMIT_HIT type
        alertService.processRateLimitHit(hit)

        return IngestResponseDto(success = true, savedCount = 1)
    }
}
