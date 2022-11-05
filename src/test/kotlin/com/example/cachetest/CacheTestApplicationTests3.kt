package com.example.cachetest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
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

    var times = 0L

    @BeforeEach
    fun initCache() {
        springCacheService.get(1)
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
            TestSupporter.test3Range.map {
                async {
                    springCacheService.get(it)
                }
            }.awaitAll()
        }
    }
}