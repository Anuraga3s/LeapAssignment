package com.leap.monitoring.collector_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class CollectorServiceApplication

fun main(args: Array<String>) {
    runApplication<CollectorServiceApplication>(*args)
}
