package com.example.cachetest

import com.example.cachetest.db.Test2
import com.example.cachetest.db.Test2Repository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NonCacheService2(
    private val test2Repository: Test2Repository
) {
    fun get(id: Long): List<Test2> {
        return test2Repository.findAllByCol4(id.toInt())
    }
}