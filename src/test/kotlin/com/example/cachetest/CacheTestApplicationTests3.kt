package com.example.cachetest

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CacheTestApplicationTests3{
    @Autowired
    protected lateinit var springCacheService: SpringCacheService3

    @BeforeEach
    fun initCache() {
        springCacheService.get(1)
    }

    @AfterEach
    fun clearCache() {
        springCacheService.clear()
    }

    @RepeatedTest(TestSupporter.repeatSize)
    fun springTest() {
        for (id in TestSupporter.test3Range) {
            springCacheService.get(id)
        }
    }
}