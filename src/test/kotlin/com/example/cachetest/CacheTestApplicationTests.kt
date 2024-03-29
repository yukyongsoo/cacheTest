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
class CacheTestApplicationTests{
    @Autowired
    protected lateinit var springCacheService: SpringCacheService

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
        for (id in TestSupporter.testRange) {
            springCacheService.get(id)
        }
    }
}

class SpringHit0: CacheTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(0L..0)
    }
}

class SpringHit10: CacheTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(TestSupporter.hitRate10)
    }
}

class SpringHit20: CacheTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(TestSupporter.hitRate20)
    }
}

class SpringHit50: CacheTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(TestSupporter.hitRate50)
    }
}

class SpringHit70: CacheTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(TestSupporter.hitRate70)
    }
}

class SpringHit100: CacheTestApplicationTests() {
    @BeforeEach
    fun initCache() {
        initCache(TestSupporter.hitRate100)
    }
}