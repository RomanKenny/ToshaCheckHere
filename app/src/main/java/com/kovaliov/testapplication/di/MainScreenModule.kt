package com.kovaliov.testapplication.di

import com.kovaliov.testapplication.data.Repository
import com.kovaliov.testapplication.domain.entities.interactors.ImageListInteractor
import com.kovaliov.testapplication.domain.entities.interactors.ImageListInteractorImpl
import com.kovaliov.testapplication.domain.entities.model.ImageListModel
import com.kovaliov.testapplication.domain.entities.model.ImageListModelImpl
import com.kovaliov.testapplication.presentation.presenter.main.ImageListPresenter
import com.kovaliov.testapplication.presentation.presenter.main.ImageListPresenterImpl
import com.kovaliov.testapplication.presentation.presenter.main.MainScreenPresenter
import com.kovaliov.testapplication.presentation.presenter.main.MainScreenPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MainScreenModule {
    @Provides
    fun provideMainScreenPresenter(): MainScreenPresenter = MainScreenPresenterImpl()

    @Singleton
    @Provides
    fun provideImageListModel(
        imageListInteractor: ImageListInteractor
    ): ImageListModel =
        ImageListModelImpl(
            imageListInteractor
        )

    @Provides
    fun provideImageListInteractor(repository: Repository):ImageListInteractor = ImageListInteractorImpl(repository)

    @Singleton
    @Provides
    fun provideImageListPresenter(imageListModel: ImageListModel): ImageListPresenter =
        ImageListPresenterImpl(imageListModel)

}