package com.stavro_xhardha.fcbarcelonashqip.ui.news.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.Topic
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface NewsMVPInteractor : MVPInteractor {
    fun getTopicsList(): Observable<List<Topic>>
}