package com.example.cachetest

import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RedisCacheService(
    private val testRepository: TestRepository
) {
    @Cacheable
    fun get(id: Long): Test? {
        return testRepository.findByIdOrNull(id)
    }
}