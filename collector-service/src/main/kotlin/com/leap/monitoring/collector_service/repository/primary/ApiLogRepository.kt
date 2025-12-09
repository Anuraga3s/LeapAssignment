package com.leap.monitoring.collector_service.repository.primary

import com.leap.monitoring.collector_service.model.logs.ApiLog
import org.springframework.data.mongodb.repository.MongoRepository

interface ApiLogRepository : MongoRepository<ApiLog, String> {

    fun findByServiceName(serviceName: String): List<ApiLog>

    fun findByServiceNameAndEndpoint(serviceName: String, endpoint: String): List<ApiLog>
}
