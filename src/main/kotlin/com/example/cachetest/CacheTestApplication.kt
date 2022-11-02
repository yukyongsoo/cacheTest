package com.example.cachetest

import com.example.cachetest.db.Test
import com.example.cachetest.db.Test2
import com.example.cachetest.db.Test3
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration


@SpringBootApplication
@EnableCaching
class CacheTestApplication

fun main(args: Array<String>) {
    runApplication<CacheTestApplication>(*args)
}

@Configuration(proxyBeanMethods = false)
class Config{
    @Bean
    fun testRedisTemplate(
        redisConnectionFactory: RedisConnectionFactory,
        objectMapper: ObjectMapper
    ): RedisTemplate<String, Test> {
        return template(redisConnectionFactory, objectMapper)
    }

    @Bean
    fun test2RedisTemplate(
        redisConnectionFactory: RedisConnectionFactory,
        objectMapper: ObjectMapper
    ): RedisTemplate<String, List<Test2>> {
        return template(redisConnectionFactory, objectMapper)
    }

    @Bean
    fun test3RedisTemplate(
        redisConnectionFactory: RedisConnectionFactory,
        objectMapper: ObjectMapper
    ): RedisTemplate<String, List<Test3>> {
        return template(redisConnectionFactory, objectMapper)
    }

    private inline fun <reified T> template(
        redisConnectionFactory: RedisConnectionFactory,
        objectMapper: ObjectMapper
    ): RedisTemplate<String, T> {
        return RedisTemplate<String, T>().apply {
            setConnectionFactory(redisConnectionFactory)
            keySerializer = stringSerializer
            valueSerializer = Jackson2JsonRedisSerializer(T::class.java).apply {
                setObjectMapper(objectMapper)
            }
        }
    }

    @Bean
    fun getObjectMapper(): ObjectMapper {
        return ObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .registerModule(JavaTimeModule())
            .registerModule(KotlinModule.Builder().build())
    }

    @Bean
    fun getCacheManager(
        connectionFactory: RedisConnectionFactory,
        objectMapper: ObjectMapper
    ): CacheManager {
        val redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    StringRedisSerializer()
                )
            )
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    GenericJackson2JsonRedisSerializer(objectMapper)
                )
            )
            .disableCachingNullValues()
            .entryTtl(Duration.ofHours(1L))

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(
            connectionFactory
        ).cacheDefaults(redisCacheConfiguration)
        .build()
    }
}