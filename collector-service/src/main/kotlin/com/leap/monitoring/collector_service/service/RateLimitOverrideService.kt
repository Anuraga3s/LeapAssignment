package com.leap.monitoring.collector_service.service

import com.leap.monitoring.collector_service.model.metadata.RateLimitOverride
import com.leap.monitoring.collector_service.repository.secondary.RateLimitOverrideRepository
import org.springframework.stereotype.Service

@Service
class RateLimitOverrideService(
    private val repo: RateLimitOverrideRepository
) {

    fun getOverride(serviceName: String): RateLimitOverride? {
        return repo.findByServiceName(serviceName)
    }

    fun setOverride(serviceName: String, newLimit: Int): RateLimitOverride {
        val override = RateLimitOverride(
            id = null,
            serviceName = serviceName,
            newLimit = newLimit,
            enabled = true
        )
        return repo.save(override)
    }

    fun disableOverride(serviceName: String) {
        val override = repo.findByServiceName(serviceName) ?: return
        val updated = override.copy(enabled = false)
        repo.save(updated)
    }
}
