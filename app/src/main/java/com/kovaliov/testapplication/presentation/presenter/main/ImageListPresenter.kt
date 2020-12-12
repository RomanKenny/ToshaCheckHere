package com.kovaliov.testapplication.presentation.presenter.main

import com.kovaliov.testapplication.domain.entities.model.ImageListModel
import com.kovaliov.testapplication.presentation.view.interfaces.ImageListScreen

interface ImageListPresenter {
    var imageListModel: ImageListModel
    var imageListScreen: ImageListScreen?
    fun onViewCreated()
    fun onAttach(imageListScreen: ImageListScreen)
    fun onDetach()
    fun loadImageList(isNeedUpdate: Boolean = false)
}