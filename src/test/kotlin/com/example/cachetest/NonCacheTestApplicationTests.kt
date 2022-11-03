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
    var times = 0L

    @RepeatedTest(TestSupporter.repeatSize)
    fun `직접 데이터베이스 조회`() {
        times = System.currentTimeMillis()

        for (id in TestSupporter.testRange) {
            nonCacheService.get(id)
        }

        println(System.currentTimeMillis() - times)
    }
}
