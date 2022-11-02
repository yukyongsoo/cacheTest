package com.example.cachetest

import com.example.cachetest.db.Test3
import com.example.cachetest.db.Test3Repository
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class SpringCacheService3(
    private val testRepository: Test3Repository,
    private val cacheManager: CacheManager
) {
    @Cacheable("spring3")
    fun get(id: Long): List<Test3> {
        return testRepository.findAll()
    }

    fun clear() {
        cacheManager.getCache("spring3")!!.clear()
    }
}