package com.stavro_xhardha.fcbarcelonashqip.ui.news_details.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.NewsBody
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface NewsTopicDetailsMVPInteractor : MVPInteractor {
    fun getNewsDetails() : Observable<NewsBody>
}