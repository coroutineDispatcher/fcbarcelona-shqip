package com.stavro_xhardha.fcbarcelonashqip.ui.matches.presenter

import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.MVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.MVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.MVPView

interface MatchesMVPPresenter<V : MVPView, I : MVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
}