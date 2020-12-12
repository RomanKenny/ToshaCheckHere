package com.kovaliov.testapplication.di

import com.kovaliov.testapplication.data.Repository
import com.kovaliov.testapplication.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}