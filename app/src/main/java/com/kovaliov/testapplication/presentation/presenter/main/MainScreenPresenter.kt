package com.kovaliov.testapplication.presentation.presenter.main

import com.kovaliov.testapplication.presentation.view.interfaces.MainScreen

interface MainScreenPresenter{
    var mainScreen: MainScreen?
    fun showScreenImageList()
    fun showScreenAbout()
    fun showScreenSupport()
    fun onAttachView(mainScreen: MainScreen)
    fun onDetachView()
}