package com.leap.monitoring.collector_service.alerting

import com.leap.monitoring.collector_service.model.metadata.AlertHistory
import com.leap.monitoring.collector_service.repository.secondary.AlertHistoryRepository
import org.springframework.stereotype.Component

@Component
class AlertPublisher(
    private val alertRepo: AlertHistoryRepository
) {
    fun publish(alert: AlertHistory) {
        alertRepo.save(alert)
    }
}
