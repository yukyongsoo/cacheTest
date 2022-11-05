package com.example.cachetest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NonCacheTestApplicationTests3(
    private val nonCacheService: NonCacheService3,
) {
    var times = 0L
    @RepeatedTest(TestSupporter.repeatSize)
    fun `직접 데이터베이스 조회`() {
        times = System.currentTimeMillis()

        runBlocking(Dispatchers.IO) {
            TestSupporter.test3Range.map {
                async {  nonCacheService.get(it) }
            }.awaitAll()
        }

        println(System.currentTimeMillis() - times)

    }
}
