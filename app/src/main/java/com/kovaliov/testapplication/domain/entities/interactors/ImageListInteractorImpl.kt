package com.kovaliov.testapplication.domain.entities.interactors

import com.kovaliov.testapplication.data.Repository

class ImageListInteractorImpl(
    override val repository: Repository
) : ImageListInteractor {
    override fun loadImageList()= repository.getData()
}