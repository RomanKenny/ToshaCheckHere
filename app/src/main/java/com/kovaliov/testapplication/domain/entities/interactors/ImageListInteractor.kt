package com.kovaliov.testapplication.domain.entities.interactors

import com.kovaliov.testapplication.data.Repository
import com.kovaliov.testapplication.data.remote.res.ImageResponse
import io.reactivex.Single

interface ImageListInteractor {
    val repository: Repository
    fun loadImageList(): Single<List<ImageResponse>>
}