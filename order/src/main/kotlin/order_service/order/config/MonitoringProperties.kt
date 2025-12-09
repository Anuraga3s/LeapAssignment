package order_service.order.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "monitoring")
class MonitoringProperties {
    lateinit var serviceName: String
    lateinit var collectorUrl: String
    var rateLimit: Int = 0
}
