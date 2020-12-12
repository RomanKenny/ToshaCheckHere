package com.kovaliov.testapplication.data.remote

import com.kovaliov.testapplication.AppConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkServiceImpl @Inject constructor(): NetworkService {
    override val api: RestService by lazy {
        val client = OkHttpClient().newBuilder()
            .build()
        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(AppConfig.BASE_URL)
            .build()
        retrofit.create(RestService::class.java)
    }
}