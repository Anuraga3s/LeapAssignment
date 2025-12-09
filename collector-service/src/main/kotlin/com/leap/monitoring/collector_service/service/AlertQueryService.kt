package com.leap.monitoring.collector_service.service

import com.leap.monitoring.collector_service.model.metadata.AlertHistory
import com.leap.monitoring.collector_service.repository.secondary.AlertHistoryRepository
import org.springframework.stereotype.Service

@Service
class AlertQueryService(
    private val repo: AlertHistoryRepository
) {

    fun getAllAlerts(): List<AlertHistory> {
        return repo.findAll()
            .sortedByDescending { it.timestamp }
    }
}
