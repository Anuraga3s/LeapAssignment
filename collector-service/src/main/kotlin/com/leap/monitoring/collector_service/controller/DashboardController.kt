package com.leap.monitoring.collector_service.controller

import com.leap.monitoring.collector_service.dto.LogSearchRequest
import com.leap.monitoring.collector_service.service.AlertQueryService
import com.leap.monitoring.collector_service.service.LogQueryService
import com.leap.monitoring.collector_service.service.StatsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dashboard")
class DashboardController(
    private val logQueryService: LogQueryService,
    private val alertQueryService: AlertQueryService,
    private val statsService: StatsService
) {

    @PostMapping("/logs/search")
    fun searchLogs(@RequestBody req: LogSearchRequest) =
        logQueryService.searchLogs(req)

    @GetMapping("/alerts")
    fun getAlerts() =
        alertQueryService.getAllAlerts()

    @GetMapping("/stats")
    fun getStats() =
        statsService.generateStats()
}
