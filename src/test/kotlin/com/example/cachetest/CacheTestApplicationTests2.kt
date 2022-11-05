package com.example.cachetest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
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

    var times = 0L

    fun initCache(hitRate: LongRange) {
        if (hitRate.first != 0L)
            hitRate.forEach {
                springCacheService.get(it)
            }
        times = System.currentTimeMillis()
    }

    @AfterEach
    fun clearCache() {
        println(System.currentTimeMillis() - times)
        springCacheService.clear()
    }

    @RepeatedTest(TestSupporter.repeatSize)
    fun springTest() {
        runBlocking(Dispatchers.IO) {
            TestSupporter.test2Range.map {
                async {
                    springCacheService.get(it)
                }
            }.awaitAll()
        }
    }
}

class Spring2Hit0: CacheTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(0L..0L)
    }
}

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