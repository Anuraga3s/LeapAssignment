package com.leap.monitoring.collector_service.service

import com.leap.monitoring.collector_service.dto.LogSearchRequest
import com.leap.monitoring.collector_service.model.logs.ApiLog
import com.leap.monitoring.collector_service.repository.primary.ApiLogRepository
import org.springframework.stereotype.Service

@Service
class LogQueryService(
    private val repo: ApiLogRepository
) {

    fun searchLogs(req: LogSearchRequest): List<ApiLog> {
        var logs = repo.findAll()

        if (req.serviceName != null)
            logs = logs.filter { it.serviceName == req.serviceName }

        if (req.endpoint != null)
            logs = logs.filter { it.endpoint == req.endpoint }

        if (req.statusCode != null)
            logs = logs.filter { it.statusCode == req.statusCode }


        req.fromTime?.let { from ->
            logs = logs.filter { it.timestamp.toEpochMilli() >= from }
        }

        req.toTime?.let { to ->
            logs = logs.filter { it.timestamp.toEpochMilli() <= to }
        }

        return logs.take(req.limit)
    }
}
