package com.example.cachetest.db

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "test3")
class Test3(
    @Id
    val id: Long,
    @Column(name = "column_1", nullable = false)
    val col1: Int,
    @Column(name = "column_2", nullable = false)
    val col2: String,
    @Column(name = "column_3", nullable = false)
    val col3: LocalDateTime,
    @Column(name = "column_4", nullable = false, columnDefinition = "TINYINT")
    val col4: Int
)