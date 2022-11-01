package com.example.cachetest

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RedisTemplateService(
    private val testRepository: TestRepository,
    private val template: RedisTemplate<String, Test>
) {
    private val keySet: List<String>

    init {
        keySet = (1L..100).map {
           makeKey(it)
        }
    }


    fun get(id: Long): Test? {
        return template.opsForValue().get(makeKey(id))
            ?: testRepository.findByIdOrNull(id)
    }

    fun addCache(id: Long) {
        val entity = testRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("찾을 수 없음")

        template.opsForValue().set(makeKey(id), entity)
    }

    fun clear() {
        template.delete(keySet)
    }

    private fun makeKey(id: Long): String {
        return "redis:${id}"
    }
}