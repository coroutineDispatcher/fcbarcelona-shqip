package com.stavro_xhardha.fcbarcelonashqip.ui.news.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.Topic
import com.stavro_xhardha.fcbarcelonashqip.network.ApiHelper
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class NewsInteractor @Inject internal constructor(apiHelper: ApiHelper): BaseInteractor(apiHelper = apiHelper)
        , NewsMVPInteractor{

    override fun getTopicsList(): Observable<List<Topic>> = apiHelper.makeTopicApiCall()
}