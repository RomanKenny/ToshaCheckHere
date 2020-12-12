package com.kovaliov.testapplication.presentation.presenter.main

import com.kovaliov.testapplication.presentation.view.interfaces.MainScreen

class MainScreenPresenterImpl(
    override var mainScreen: MainScreen? = null
) : MainScreenPresenter {
    override fun showScreenImageList() {
        mainScreen?.showScreenImageList()
    }

    override fun showScreenAbout() {
        mainScreen?.showScreenAbout()
    }

    override fun showScreenSupport() {
        mainScreen?.showScreenSupport()
    }

    override fun onAttachView(mainScreen: MainScreen) {
        this.mainScreen = mainScreen
    }

    override fun onDetachView() {
        this.mainScreen = null
    }
}