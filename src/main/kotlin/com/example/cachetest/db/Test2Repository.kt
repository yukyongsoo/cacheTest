package com.example.cachetest.db

import org.springframework.data.jpa.repository.JpaRepository

interface Test2Repository: JpaRepository<Test2, Long> {
    fun findAllByCol4(col4: Int): List<Test2>
}