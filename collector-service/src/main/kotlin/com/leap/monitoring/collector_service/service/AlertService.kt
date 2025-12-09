package com.leap.monitoring.collector_service.service

import com.leap.monitoring.collector_service.alerting.AlertPublisher
import com.leap.monitoring.collector_service.alerting.AlertRuleEvaluator
import com.leap.monitoring.collector_service.model.logs.ApiLog
import com.leap.monitoring.collector_service.model.logs.RateLimitHitLog
import com.leap.monitoring.collector_service.model.metadata.AlertHistory
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AlertService(
    private val evaluator: AlertRuleEvaluator,
    private val publisher: AlertPublisher
) {

    fun processApiLog(log: ApiLog) {

        if (evaluator.checkLatencyAlert(log)) {
            publish("HIGH_LATENCY", log.serviceName, log.endpoint, "Latency exceeded: ${log.latencyMs} ms")
        }

        if (evaluator.checkErrorAlert(log)) {
            publish("SERVER_ERROR", log.serviceName, log.endpoint, "5xx status code: ${log.statusCode}")
        }
    }

    fun processRateLimitHit(hit: RateLimitHitLog) {
        if (evaluator.checkRateLimitAlert(hit)) {
            publish("RATE_LIMIT_HIT", hit.serviceName, hit.endpoint ?: "", "Exceeded rate limit (${hit.limit})")
        }
    }

    private fun publish(type: String, service: String, endpoint: String, msg: String) {
        publisher.publish(
            AlertHistory(
                serviceName = service,
                endpoint = endpoint,
                alertType = type,
                message = msg,
                timestamp = Instant.now()
            )
        )
    }
}
