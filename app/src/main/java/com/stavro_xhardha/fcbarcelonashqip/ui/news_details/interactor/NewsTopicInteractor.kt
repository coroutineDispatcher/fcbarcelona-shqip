package com.stavro_xhardha.fcbarcelonashqip.ui.news_details.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.NewsBody
import com.stavro_xhardha.fcbarcelonashqip.network.ApiHelper
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class NewsTopicInteractor @Inject internal constructor(apiHelper: ApiHelper) : BaseInteractor(apiHelper = apiHelper)
        , NewsTopicDetailsMVPInteractor {

    override fun getNewsDetails(): Observable<NewsBody> = apiHelper.makeNewsBodyApiCall()
}