package com.leap.monitoring.collector_service.controller

import com.leap.monitoring.collector_service.service.RateLimitOverrideService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rate-limit")
class RateLimitOverrideController(
    private val service: RateLimitOverrideService
) {

    @GetMapping("/{serviceName}")
    fun get(@PathVariable serviceName: String) =
        service.getOverride(serviceName)

    @PostMapping("/{serviceName}/{limit}")
    fun set(@PathVariable serviceName: String, @PathVariable limit: Int) =
        service.setOverride(serviceName, limit)

    @DeleteMapping("/{serviceName}")
    fun disable(@PathVariable serviceName: String) =
        service.disableOverride(serviceName)
}
