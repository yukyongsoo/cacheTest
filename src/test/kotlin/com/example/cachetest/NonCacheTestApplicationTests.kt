package com.example.cachetest

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NonCacheTestApplicationTests(
    private val nonCacheService: NonCacheService,
) {
    private val testRange = 1L..100L

    @RepeatedTest(1000)
    fun `직접 데이터베이스 조회`() {
        for (id in testRange) {
            nonCacheService.get(id)
        }
    }
}
