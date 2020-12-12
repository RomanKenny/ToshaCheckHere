package com.kovaliov.testapplication.presentation.presenter.main

import android.util.Log
import com.kovaliov.testapplication.domain.entities.model.ImageListModel
import com.kovaliov.testapplication.presentation.view.interfaces.ImageListScreen

class ImageListPresenterImpl (
    override var imageListModel: ImageListModel,
    override var imageListScreen: ImageListScreen? = null
) : ImageListPresenter {

    init{
        Log.d("DEBUG", "init presenter")
    }
    override fun onViewCreated() {
       loadImageList()
    }

    override fun onAttach(imageListScreen: ImageListScreen) {
        this.imageListScreen = imageListScreen
    }

    override fun onDetach() {
        this.imageListScreen = null
    }

    override fun loadImageList(isNeedUpdate: Boolean) {
        if(imageListScreen?.isNetworkAvailable() != true){
            imageListScreen?.showLoadingError("Интернет не доступен")
            return
        }
        imageListScreen?.showLoading(true)
        imageListModel.loadImageList(isNeedUpdate) {
            imageListScreen?.showLoading(false)
            showDataFromModel(imageListModel)
        }
    }

    private fun showDataFromModel(imageListModel: ImageListModel) {
        imageListModel.apply {
            when {
                errorInfo != null -> {
                    imageListScreen?.showLoadingError(
                        errorInfo ?: "Во время загрузки произошла ошибка."
                    )
                }
                inputImageList.isEmpty() -> {
                    imageListScreen?.showEmptyData()
                }
                inputImageList.isNotEmpty() -> {
                    imageListScreen?.showImages(inputImageList)
                }
            }
        }
    }
}