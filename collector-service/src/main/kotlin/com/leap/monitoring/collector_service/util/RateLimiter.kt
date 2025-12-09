package com.leap.monitoring.collector_service.util

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

/**
 * Backend rate-limiter is optional.
 * SDK enforces actual limits.
 * This is used only for preview or debugging.
 */
object RateLimiter {

    private val counters = ConcurrentHashMap<String, AtomicInteger>()

    fun increment(service: String): Int {
        val counter = counters.computeIfAbsent(service) { AtomicInteger(0) }
        return counter.incrementAndGet()
    }

    fun reset(service: String) {
        counters[service]?.set(0)
    }
}
