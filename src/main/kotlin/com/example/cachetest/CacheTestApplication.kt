package com.example.cachetest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@SpringBootApplication
class CacheTestApplication(

)

fun main(args: Array<String>) {
    runApplication<CacheTestApplication>(*args)
}

@Configuration(proxyBeanMethods = false)
class Config {
    @Bean
    fun testRedisTemplate(
        redisConnectionFactory: RedisConnectionFactory
    ): RedisTemplate<Long, Test> {
        return RedisTemplate<Long, Test>().apply {
            setConnectionFactory(redisConnectionFactory)
        }
    }
}