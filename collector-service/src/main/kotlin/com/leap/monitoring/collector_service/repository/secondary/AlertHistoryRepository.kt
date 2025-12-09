package com.leap.monitoring.collector_service.repository.secondary

import com.leap.monitoring.collector_service.model.metadata.AlertHistory
import org.springframework.data.mongodb.repository.MongoRepository

interface AlertHistoryRepository : MongoRepository<AlertHistory, String> {

    fun findByServiceName(serviceName: String): List<AlertHistory>
}
