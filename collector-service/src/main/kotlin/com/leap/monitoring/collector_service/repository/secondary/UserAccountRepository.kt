package com.leap.monitoring.collector_service.repository.secondary

import com.leap.monitoring.collector_service.model.UserAccount
import org.springframework.data.mongodb.repository.MongoRepository

interface UserAccountRepository : MongoRepository<UserAccount, String> {
    fun findByUsername(username: String): UserAccount?
}
