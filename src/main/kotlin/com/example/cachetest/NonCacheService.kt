package com.example.cachetest

import com.example.cachetest.db.Test
import com.example.cachetest.db.TestRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NonCacheService(
    private val testRepository: TestRepository
) {
    fun get(id: Long): Test? {
        return testRepository.findByIdOrNull(id)
    }
}