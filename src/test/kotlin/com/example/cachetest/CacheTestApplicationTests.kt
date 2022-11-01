package com.example.cachetest

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CacheTestApplicationTests(
    private val nonCacheService: NonCacheService,
    private val springCacheService: SpringCacheService,
    private val redisTemplateService: RedisTemplateService
) {
    private val hitRate = 1L..75L

    private val testRange = 1L..100L

    @BeforeEach
    fun initCache() {
        hitRate.forEach {
            springCacheService.get(it)
            redisTemplateService.addCache(it)
        }
    }

    @AfterEach
    fun clearCache() {
        springCacheService.clear()
        redisTemplateService.clear()
    }

    @RepeatedTest(100)
    fun `직접 데이터베이스 조회`() {
        for (id in testRange) {
            nonCacheService.get(id)
        }

        listOf<String>().distinct()
    }

    @RepeatedTest(100)
    fun `스프링 캐시 조회`() {
        for (id in testRange) {
            springCacheService.get(id)
        }
    }

    @RepeatedTest(100)
    fun `레디스 템플릿 조회`() {
        for (id in testRange) {
            redisTemplateService.get(id)
        }
    }
}
