package com.leap.monitoring.collector_service.controller

import com.leap.monitoring.collector_service.dto.ApiLogDto
import com.leap.monitoring.collector_service.dto.IngestResponseDto
import com.leap.monitoring.collector_service.dto.RateLimitHitDto
import com.leap.monitoring.collector_service.service.IngestService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ingest")
class IngestController(
    private val ingestService: IngestService
) {

    // Single API log
    @PostMapping("/log")
    fun ingestLog(@RequestBody log: ApiLogDto): IngestResponseDto {
        return ingestService.saveApiLog(log)
    }

    // Bulk API logs
    @PostMapping("/logs")
    fun ingestLogs(@RequestBody logs: List<ApiLogDto>): IngestResponseDto {
        return ingestService.saveApiLogs(logs)
    }

    // Rate limit hit
    @PostMapping("/rate-limit-hit")
    fun rateLimitHit(@RequestBody hit: RateLimitHitDto): IngestResponseDto {
        return ingestService.saveRateLimitHit(hit)
    }
}
