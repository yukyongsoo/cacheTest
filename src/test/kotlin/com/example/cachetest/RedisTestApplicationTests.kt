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
class RedisTestApplicationTests{
    @Autowired
    protected lateinit var redisTemplateService: RedisTemplateService

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
            TestSupporter.testRange.map {
                async {
                    redisTemplateService.get(it)
                }
            }.awaitAll()
        }
    }
}

class RedisHit0: RedisTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(0L..0L)
    }
}

class RedisHit10: RedisTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(TestSupporter.hitRate10)
    }
}

class RedisHit20: RedisTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(TestSupporter.hitRate20)
    }
}

class RedisHit50: RedisTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(TestSupporter.hitRate50)
    }
}

class RedisHit70: RedisTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(TestSupporter.hitRate70)
    }
}

class RedisHit100: RedisTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(TestSupporter.hitRate100)
    }
}
