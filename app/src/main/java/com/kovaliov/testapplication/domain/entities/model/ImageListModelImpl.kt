package com.kovaliov.testapplication.domain.entities.model

import android.util.Log
import com.kovaliov.testapplication.data.local.ImageApiRequestCounterService
import com.kovaliov.testapplication.domain.entities.InputImage
import com.kovaliov.testapplication.domain.entities.interactors.ImageListInteractor
import com.kovaliov.testapplication.domain.entities.toInputImageList
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class ImageListModelImpl(
    override val imageListInteractor: ImageListInteractor
) : ImageListModel {
    override val inputImageList: LinkedList<InputImage> = LinkedList()
    override var errorInfo: String? = null
    //private val imageApiRequestCounterService: ImageApiRequestCounterService = ImageApiRequestCounterService()

    init {
        Log.d("DEBUG", "init ImageListModelImpl")
    }

    override fun loadImageList(isNeedUpdate: Boolean, onLoadDataComplete: () -> Unit) {
        if (!isNeedUpdate && inputImageList.isNotEmpty()) {
            onLoadDataComplete.invoke()
            return
        }
        inputImageList.clear()
        imageListInteractor.loadImageList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                Single.fromCallable {
                    it.toInputImageList()
                }
            }.doOnSuccess {inputImageList ->
                parseResult(inputImageList)
                onLoadDataComplete.invoke()
                Log.d("DEBUG", "onLoadDataComplete.invoke() in RxJava")
            }
            .doOnError {error ->
                parseError(error)
                onLoadDataComplete.invoke()}
            .subscribe()
    }

    private fun parseError(error: Throwable) {
        errorInfo = error.toString()
    }

    private fun parseResult(responseResult: List<InputImage>) {
        errorInfo = null
        inputImageList.addAll(responseResult)
    }
}