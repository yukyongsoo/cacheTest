package com.example.cachetest

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedisTestApplicationTests(
    private val redisTemplateService: RedisTemplateService
) {
    private val hitRateArray = arrayOf(
        1L..0L,
        1L..10L,
        1L..25L,
        1L..50L,
        1L..75L,
        1L..100L,
    )

    private val hitRate = hitRateArray[0]

    private val testRange = 1L..100L

    @BeforeEach
    fun initCache() {
        hitRate.forEach {
            redisTemplateService.addCache(it)
        }
    }

    @AfterEach
    fun clearCache() {
        redisTemplateService.clear()
    }

    @RepeatedTest(1000)
    fun `레디스 템플릿 조회`() {
        for (id in testRange) {
            redisTemplateService.get(id)
        }
    }
}
