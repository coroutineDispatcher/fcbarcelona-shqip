package com.stavro_xhardha.fcbarcelonashqip.ui.home.presenter

import com.stavro_xhardha.fcbarcelonashqip.events.ExpandNewsSelectedTopicEvent
import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.MVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.home.interactor.HomeMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.home.view.HomeMVPView

interface HomeMVPPresenter<V : HomeMVPView, I : HomeMVPInteractor> : MVPPresenter<V, I> {
    fun onNavNewsItemClick(): Unit?
    fun onNewsTopicsItemCardClick(): Unit?
    fun updateCacheData(event: ExpandNewsSelectedTopicEvent)
    fun onNavTableItemClick(): Unit?
}