package com.example.cachetest

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NonCacheTestApplicationTests2(
    private val nonCacheService: NonCacheService2,
) {
    @RepeatedTest(TestSupporter.repeatSize)
    fun `직접 데이터베이스 조회`() {
        for (id in TestSupporter.test2Range) {
            nonCacheService.get(id)
        }
    }
}