package com.leap.monitoring.collector_service.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(
    basePackages = ["com.leap.monitoring.collector_service.repository.secondary"],
    mongoTemplateRef = "secondaryMongoTemplate"
)
class SecondaryMongoConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
    fun secondaryMongoProperties(): MongoProperties = MongoProperties()

    @Bean
    fun secondaryMongoClient(
        @Qualifier("secondaryMongoProperties") props: MongoProperties
    ): MongoClient = MongoClients.create(props.uri)

    @Bean
    fun secondaryMongoDatabaseFactory(
        @Qualifier("secondaryMongoClient") client: MongoClient,
        @Qualifier("secondaryMongoProperties") props: MongoProperties
    ): MongoDatabaseFactory = SimpleMongoClientDatabaseFactory(client, props.database)

    @Bean
    fun secondaryMongoTemplate(
        @Qualifier("secondaryMongoDatabaseFactory") factory: MongoDatabaseFactory
    ): MongoTemplate = MongoTemplate(factory)
}
