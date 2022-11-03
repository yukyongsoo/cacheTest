package com.example.cachetest

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled
class CacheTestApplicationTests2{
    @Autowired
    protected lateinit var springCacheService: SpringCacheService2

    fun initCache(hitRate: LongRange) {
        hitRate.forEach {
            springCacheService.get(it)
        }
    }

    @AfterEach
    fun clearCache() {
        springCacheService.clear()
    }

    @RepeatedTest(TestSupporter.repeatSize)
    fun springTest() {
        for (id in TestSupporter.test2Range) {
            springCacheService.get(id)
        }
    }
}

class Spring2Hit0: CacheTestApplicationTests2()

class Spring2Hit10: CacheTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(1L..1L)
    }
}

class Spring2Hit20: CacheTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(1L..2L)
    }
}

class Spring2Hit50: CacheTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(1L..5L)
    }
}

class Spring2Hit70: CacheTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(1L..7L)
    }
}

class Spring2Hit100: CacheTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(1L..10L)
    }
}