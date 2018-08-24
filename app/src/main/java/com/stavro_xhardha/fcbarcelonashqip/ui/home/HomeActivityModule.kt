package com.stavro_xhardha.fcbarcelonashqip.ui.home

import com.stavro_xhardha.fcbarcelonashqip.ui.home.interactor.HomeInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.home.interactor.HomeMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.home.presenter.HomeMVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.home.presenter.HomePresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.home.view.HomeMVPView
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
class HomeActivityModule {

    @Provides
    internal fun provideHomeInteractor(homeInteractor: HomeInteractor): HomeMVPInteractor = homeInteractor

    @Provides
    internal fun provideHomePresenter(homePresenter: HomePresenter<HomeMVPView, HomeMVPInteractor>):
            HomeMVPPresenter<HomeMVPView, HomeMVPInteractor> = homePresenter
}