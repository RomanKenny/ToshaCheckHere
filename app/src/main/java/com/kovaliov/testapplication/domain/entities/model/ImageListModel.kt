package com.kovaliov.testapplication.domain.entities.model

import com.kovaliov.testapplication.domain.entities.InputImage
import com.kovaliov.testapplication.domain.entities.interactors.ImageListInteractor
import java.util.*

interface ImageListModel {
    val imageListInteractor: ImageListInteractor
    val inputImageList: LinkedList<InputImage>
    var errorInfo: String?
    fun loadImageList(isNeedUpdate: Boolean,onLoadDataComplete: () -> Unit)
}