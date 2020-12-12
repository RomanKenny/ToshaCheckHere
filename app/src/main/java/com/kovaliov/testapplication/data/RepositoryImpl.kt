package com.kovaliov.testapplication.data

import com.kovaliov.testapplication.App
import com.kovaliov.testapplication.R
import com.kovaliov.testapplication.data.database.Database
import com.kovaliov.testapplication.data.local.ImageApiRequestCounter
import com.kovaliov.testapplication.data.local.ImageApiRequestCounterService
import com.kovaliov.testapplication.data.remote.NetworkService
import com.kovaliov.testapplication.data.remote.res.ImageResponse
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : Repository {
    override fun getData(): Single<List<ImageResponse>> {
        ImageApiRequestCounterService.incrementRequestAmount()

        return networkService.api.getImages()


    }

}