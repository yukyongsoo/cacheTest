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
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled
class RedisTestApplicationTests2{
    @Autowired
    protected lateinit var redisTemplateService: RedisTemplateService2

    var times = 0L

    fun initCache(hitRate: LongRange) {
        if (hitRate.first != 0L)
            hitRate.forEach {
                redisTemplateService.addCache(it)
            }

        times = System.currentTimeMillis()
    }

    @AfterEach
    fun clearCache() {
        println(System.currentTimeMillis() - times)

        redisTemplateService.clear()
    }

    @RepeatedTest(TestSupporter.repeatSize)
    fun `레디스 템플릿 조회`() {
        runBlocking(Dispatchers.IO) {
            TestSupporter.test2Range.map {
                async {
                    redisTemplateService.get(it)
                }
            }.awaitAll()
        }
    }
}

class Redis2Hit0: RedisTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(0L..0L)
    }
}

class Redis2Hit10: RedisTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(1L..1L)
    }
}

class Redis2Hit20: RedisTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(1L..2L)
    }
}

class Redis2Hit50: RedisTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(1L..5L)
    }
}

class Redis2Hit70: RedisTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(1L..7L)
    }
}

class Redis2Hit100: RedisTestApplicationTests2() {
    @BeforeEach
    fun initCache() {
        initCache(1L..10L)
    }
}
