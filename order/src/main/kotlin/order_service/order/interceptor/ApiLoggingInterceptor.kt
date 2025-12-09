package order_service.order.interceptor

import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.client.RestTemplate
import order_service.order.model.ApiLog

@Component
class ApiLoggingInterceptor(
    private val restTemplate: RestTemplate
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.setAttribute("startTime", System.currentTimeMillis())
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {

        println("🔥 Interceptor executed for: ${request.requestURI}")

        val startTime = request.getAttribute("startTime") as Long
        val duration = System.currentTimeMillis() - startTime

        val log = ApiLog(
            serviceName = "order-service",
            endpoint = request.requestURI,
            method = request.method,
            status = response.status,
            requestSize = request.contentLengthLong,
            responseSize = response.getHeader("Content-Length")?.toLongOrNull() ?: 0L,
            latency = duration,
            timestamp = System.currentTimeMillis()
        )


        try {
            println("➡ Sending log to collector-service...")
            restTemplate.postForObject("http://localhost:8080/api/logs", log, String::class.java)
            println("Log sent!")
        } catch (e: Exception) {
            println("Failed to send log: ${e.message}")
        }
    }

}
