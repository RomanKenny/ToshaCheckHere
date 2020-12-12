package com.kovaliov.testapplication.di

import com.kovaliov.testapplication.data.remote.NetworkService
import com.kovaliov.testapplication.data.remote.NetworkServiceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NetworkModule {
    @Singleton
    @Binds
    fun bind(networkServiceImpl: NetworkServiceImpl): NetworkService
}