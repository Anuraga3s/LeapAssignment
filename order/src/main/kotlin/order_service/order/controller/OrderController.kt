package order_service.order.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/order")
class OrderController {

    @GetMapping("/status")
    fun checkStatus(): String {
        return "Order service is running"
    }

    @GetMapping("/test")
    fun test(): String {
        return "Order service is working"
    }

    @PostMapping("/create")
    fun createOrder(): String {
        // simulate some processing
        Thread.sleep(200)
        return "Order created successfully"
    }

    @GetMapping("/list")
    fun listOrders(): List<String> {
        Thread.sleep(150)
        return listOf("order1", "order2", "order3")
    }
}
