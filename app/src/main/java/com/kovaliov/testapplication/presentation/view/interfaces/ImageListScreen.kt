package com.kovaliov.testapplication.presentation.view.interfaces

import com.kovaliov.testapplication.domain.entities.InputImage

interface ImageListScreen {

    fun showLoading(state: Boolean)
    fun showLoadingError(error: String)
    fun showEmptyData()
    fun showImages(inputImageList: List<InputImage>)
    fun onChoice(inputImage: InputImage)
    fun isNetworkAvailable(): Boolean
}