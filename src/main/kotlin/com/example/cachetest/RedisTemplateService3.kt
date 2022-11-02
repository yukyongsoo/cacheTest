package com.example.cachetest

import com.example.cachetest.db.Test3
import com.example.cachetest.db.Test3Repository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RedisTemplateService3(
    private val test3Repository: Test3Repository,
    private val template: RedisTemplate<String, List<Test3>>
) {
    private val keySet: List<String>

    init {
        keySet = (1L..100).map {
           makeKey(it)
        }
    }


    fun get(id: Long): List<Test3> {
        return template.opsForValue().get(makeKey(id))
            ?: test3Repository.findAll()
    }

    fun addCache(id: Long) {
        val entity = test3Repository.findAll()

        template.opsForValue().set(makeKey(id), entity)
    }

    fun clear() {
        template.delete(keySet)
    }

    private fun makeKey(id: Long): String {
        return "redis3:${id}"
    }
}