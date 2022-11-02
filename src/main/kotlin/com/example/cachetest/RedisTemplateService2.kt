package com.example.cachetest

import com.example.cachetest.db.Test2
import com.example.cachetest.db.Test2Repository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RedisTemplateService2(
    private val test2Repository: Test2Repository,
    private val template: RedisTemplate<String, List<Test2>>
) {
    private val keySet: List<String>

    init {
        keySet = (1L..100).map {
           makeKey(it)
        }
    }


    fun get(id: Long): List<Test2> {
        return template.opsForValue().get(makeKey(id))
            ?: test2Repository.findAllByCol4(id.toInt())
    }

    fun addCache(id: Long) {
        val entity = test2Repository.findAllByCol4(id.toInt())

        template.opsForValue().set(makeKey(id), entity)
    }

    fun clear() {
        template.delete(keySet)
    }

    private fun makeKey(id: Long): String {
        return "redis2:${id}"
    }
}