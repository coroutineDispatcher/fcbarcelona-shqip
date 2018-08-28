package com.stavro_xhardha.fcbarcelonashqip.ui.news.presenter

import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.MVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.news.interactor.NewsMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.news.view.NewsMVPView

interface NewsMVPPresenter<V : NewsMVPView, I : NewsMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
}