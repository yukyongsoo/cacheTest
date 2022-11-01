package com.example.cachetest

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SpringCacheService(
    private val testRepository: TestRepository,
    private val cacheManager: CacheManager
) {
    @Cacheable("spring")
    fun get(id: Long): Test? {
        return testRepository.findByIdOrNull(id)
    }

    fun clear() {
        cacheManager.getCache("spring")!!.clear()
    }
}