package com.kovaliov.testapplication.data

import com.kovaliov.testapplication.data.remote.res.ImageResponse
import io.reactivex.Single

interface Repository {
    fun getData(): Single<List<ImageResponse>>
}