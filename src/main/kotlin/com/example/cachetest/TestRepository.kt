package com.example.cachetest

import org.springframework.data.jpa.repository.JpaRepository

interface TestRepository: JpaRepository<Test, Long>