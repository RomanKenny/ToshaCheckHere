package com.kovaliov.testapplication.data.remote

import com.kovaliov.testapplication.data.remote.res.ImageResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RestService {
    @GET("search?limit=50") //изменяем лимит на поиск картинок.
    fun getImages(): Single<List<ImageResponse>>

}