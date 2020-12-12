package com.kovaliov.testapplication.data.local

interface ImageApiRequestCounter {
    fun getRequestAmount(): Long
    fun incrementRequestAmount()
    fun clearRequestAmount()


}