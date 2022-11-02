package com.example.cachetest

import com.example.cachetest.db.Test2
import com.example.cachetest.db.Test3
import com.example.cachetest.db.Test3Repository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NonCacheService3(
    private val test3Repository: Test3Repository
) {
    fun get(id: Long) {
        test3Repository.findAll()
    }
}