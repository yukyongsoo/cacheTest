package com.example.cachetest

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedisTestApplicationTests3{
    @Autowired
    protected lateinit var redisTemplateService: RedisTemplateService3

    @BeforeEach
    fun initCache() {
        redisTemplateService.addCache(0)
    }

    @AfterEach
    fun clearCache() {
        redisTemplateService.clear()
    }

    @RepeatedTest(TestSupporter.repeatSize)
    fun `레디스 템플릿 조회`() {
        for (id in TestSupporter.test3Range) {
            redisTemplateService.get(id)
        }
    }
}