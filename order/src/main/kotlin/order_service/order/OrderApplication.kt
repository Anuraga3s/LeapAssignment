package order_service.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import order_service.order.config.MonitoringProperties


@SpringBootApplication
@EnableConfigurationProperties(MonitoringProperties::class)
class OrderApplication

fun main(args: Array<String>) {
	runApplication<OrderApplication>(*args)
}
