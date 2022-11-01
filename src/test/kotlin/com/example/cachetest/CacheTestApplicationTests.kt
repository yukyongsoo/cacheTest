package com.example.cachetest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CacheTestApplicationTests(
    private var nonCacheService: NonCacheService,
    private var springCacheService: SpringCacheService,
    private var redisTemplateService: RedisTemplateService
) {
    init {
        val hitRate = 1L..50L

        hitRate.forEach {
            springCacheService.get(it)
            redisTemplateService.addCache(it)
        }
    }

    @Test
    fun `직접 데이터베이스 조회`() {

    }
}
