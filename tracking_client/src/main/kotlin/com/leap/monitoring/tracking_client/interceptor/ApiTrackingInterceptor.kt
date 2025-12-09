package com.leap.monitoring.tracking_client.interceptor

import com.leap.monitoring.tracking_client.model.ApiLog
import com.leap.monitoring.tracking_client.rate_limiter.RateLimiter
import org.springframework.http.HttpRequest
import org.springframework.http.client.*
import org.springframework.web.client.RestTemplate
import java.io.ByteArrayOutputStream

class ApiTrackingInterceptor(
    private val serviceName: String,
    private val collectorUrl: String,
    private val restTemplate: RestTemplate,
    private val rateLimiter: RateLimiter
) : ClientHttpRequestInterceptor {

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {

        val start = System.currentTimeMillis()
        val response = execution.execute(request, body)
        val latency = System.currentTimeMillis() - start

        val log = ApiLog(
            serviceName = serviceName,
            endpoint = request.uri.toString(),
            method = request.method.name(),
            status = response.statusCode.value(),
            requestSize = body.size.toLong(),
            responseSize = getResponseSize(response),
            latency = latency
        )

        // RATE LIMIT check
        if (rateLimiter.isRateLimitExceeded(serviceName)) {
            restTemplate.postForObject("$collectorUrl/collector/log",
                log.copy(status = 429), String::class.java
            )
            return response
        }

        // NORMAL log
        restTemplate.postForObject("$collectorUrl/collector/log",
            log, String::class.java
        )

        return response
    }

    private fun getResponseSize(response: ClientHttpResponse): Long {
        return try {
            val buffer = ByteArrayOutputStream()
            response.body.copyTo(buffer)
            buffer.size().toLong()
        } catch (e: Exception) {
            0
        }
    }
}
