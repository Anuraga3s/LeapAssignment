package com.leap.monitoring.tracking_client.config

import com.leap.monitoring.tracking_client.interceptor.ApiTrackingInterceptor
import com.leap.monitoring.tracking_client.rate_limiter.RateLimiter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ApiTrackingAutoConfig {

    @Value("\${monitoring.service-name}")
    lateinit var serviceName: String

    @Value("\${monitoring.collector-url}")
    lateinit var collectorUrl: String

    @Value("\${monitoring.rate-limit:100}")
    var rateLimit: Int = 100

    @Bean
    fun restTemplate(): RestTemplate {
        val template = RestTemplate()
        template.interceptors.add(
            ApiTrackingInterceptor(
                serviceName,
                collectorUrl,
                template,
                RateLimiter(rateLimit)
            )
        )
        return template
    }
}
