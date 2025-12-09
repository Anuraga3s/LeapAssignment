package order_service.order.model

data class ApiLog(
    val id: String? = null,
    val serviceName: String,
    val endpoint: String,
    val method: String,
    val status: Int,
    val requestSize: Long,
    val responseSize: Long,
    val latency: Long,
    val timestamp: Long
)
