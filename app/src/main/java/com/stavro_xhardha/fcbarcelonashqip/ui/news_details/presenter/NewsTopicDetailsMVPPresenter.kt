package com.stavro_xhardha.fcbarcelonashqip.ui.news_details.presenter

import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.MVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.news_details.interactor.NewsTopicDetailsMVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.news_details.view.NewsTopicDetailsMVPView

interface NewsTopicDetailsMVPPresenter<V : NewsTopicDetailsMVPView
        , I : NewsTopicDetailsMVPInteractor> : MVPPresenter<V, I> {
    fun onViewPrepared()
}