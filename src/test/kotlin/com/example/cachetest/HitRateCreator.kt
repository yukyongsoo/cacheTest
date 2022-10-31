package com.example.cachetest

class HitRateCreator(
    private val histRate: Int
) {
    private val hitArray = arrayOfNulls<Long>(100)
    private var total = 0
    private var hit = 0
    private var rate = 0.0

    fun getRandomId(): Long {
        return 0
    }
}