package com.kovaliov.testapplication.data.local

object ImageApiRequestCounterService : ImageApiRequestCounter {

    private var amount: Long = 0


    override fun getRequestAmount(): Long {
        return amount

    }

    override fun incrementRequestAmount() {
        amount++
    }

    override fun clearRequestAmount() {
        amount = 0
    }

}