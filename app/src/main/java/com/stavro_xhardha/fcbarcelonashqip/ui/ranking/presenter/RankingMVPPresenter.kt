package com.stavro_xhardha.fcbarcelonashqip.ui.ranking.presenter

import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.MVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.interactor.RankingMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.ranking.view.RankingMVPView

interface RankingMVPPresenter<V : RankingMVPView, I : RankingMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
}