package com.stavro_xhardha.fcbarcelonashqip.network

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.model.NewsBody
import com.stavro_xhardha.fcbarcelonashqip.model.Standing
import com.stavro_xhardha.fcbarcelonashqip.model.TableItem
import com.stavro_xhardha.fcbarcelonashqip.model.Topic
import io.reactivex.Observable
import javax.inject.Inject

class AppApiHelper @Inject constructor() : ApiHelper {

    override fun makeTopicApiCall(): Observable<List<Topic>> =
            Rx2AndroidNetworking.get(Brain.NEWS_URL)
                    .build()
                    .getObjectListObservable(Topic::class.java)

    override fun makeNewsBodyApiCall(): Observable<NewsBody> =
            Rx2AndroidNetworking.get(Brain.NEWS_BODY + Brain.newsId)
                    .build()
                    .getObjectObservable(NewsBody::class.java)

    override fun makeRankingApiCall(): Observable<Standing> =
            Rx2AndroidNetworking.get(Brain.TABLE_URL_V2)
                    .addHeaders(Brain.AUTHORIZATION, Brain.TOKEN)
                    .build()
                    .getObjectObservable(Standing::class.java)
}