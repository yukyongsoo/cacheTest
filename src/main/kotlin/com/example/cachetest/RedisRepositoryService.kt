package com.example.cachetest

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RedisRepositoryService(
    private val testRepository: TestRepository,
    private val template: RedisTemplate<Long, Test>
) {
    fun get(id: Long): Test? {
        return template.opsForValue().get(id)
            ?: testRepository.findByIdOrNull(id)
    }
}