package com.kovaliov.testapplication.di

import com.kovaliov.testapplication.presentation.view.impl.main.ImageListFragment
import com.kovaliov.testapplication.presentation.view.impl.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainScreenModule::class, NetworkModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun injectMainScreen(mainActivity: MainActivity)
    fun injectImageListFragment(imageListFragment: ImageListFragment)
}