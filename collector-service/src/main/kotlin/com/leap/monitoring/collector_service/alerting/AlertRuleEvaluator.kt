package com.leap.monitoring.collector_service.alerting

import com.leap.monitoring.collector_service.model.logs.ApiLog
import com.leap.monitoring.collector_service.model.logs.RateLimitHitLog
import org.springframework.stereotype.Component

@Component
class AlertRuleEvaluator {

    fun checkLatencyAlert(log: ApiLog): Boolean =
        log.latencyMs > 500

    fun checkErrorAlert(log: ApiLog): Boolean =
        log.statusCode in 500..599

    fun checkRateLimitAlert(hit: RateLimitHitLog): Boolean =
        true

    // HIGH ERROR RATE is OPTIONAL (assignment does not strictly require it)
    fun errorRatePercent(error: Int, total: Int): Double =
        if (total == 0) 0.0 else (error.toDouble() / total.toDouble()) * 100.0
}
