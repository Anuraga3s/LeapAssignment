package com.leap.monitoring.tracking_client.rate_limiter

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class RateLimiter(
    private val defaultLimit: Int = 100
) {

    private val counters = ConcurrentHashMap<String, AtomicInteger>()
    private var lastResetTime = System.currentTimeMillis()

    fun isRateLimitExceeded(serviceName: String): Boolean {
        resetIfNeeded()

        val counter = counters.computeIfAbsent(serviceName) { AtomicInteger(0) }
        val count = counter.incrementAndGet()

        return count > defaultLimit
    }

    private fun resetIfNeeded() {
        val now = System.currentTimeMillis()
        if (now - lastResetTime >= 1000) {
            counters.clear()
            lastResetTime = now
        }
    }
}
