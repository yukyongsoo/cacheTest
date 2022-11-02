package com.example.cachetest.db

import org.springframework.data.jpa.repository.JpaRepository

interface TestRepository: JpaRepository<Test, Long>