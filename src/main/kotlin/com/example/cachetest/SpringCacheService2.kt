package com.example.cachetest

import com.example.cachetest.db.Test2
import com.example.cachetest.db.Test2Repository
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class SpringCacheService2(
    private val testRepository: Test2Repository,
    private val cacheManager: CacheManager
) {
    @Cacheable("spring2")
    fun get(id: Long): List<Test2> {
        return testRepository.findAllByCol4(id.toInt())
    }

    fun clear() {
        cacheManager.getCache("spring2")!!.clear()
    }
}