package com.stavro_xhardha.fcbarcelonashqip.network

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.stavro_xhardha.fcbarcelonashqip.brain.Brain
import com.stavro_xhardha.fcbarcelonashqip.model.NewsBody
import com.stavro_xhardha.fcbarcelonashqip.model.ranking.Standing
import com.stavro_xhardha.fcbarcelonashqip.model.Topic
import com.stavro_xhardha.fcbarcelonashqip.model.match.MatchResponse
import com.stavro_xhardha.fcbarcelonashqip.model.team.TeamResponse
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

    override fun makeMatchesApiCall(): Observable<MatchResponse> =
            Rx2AndroidNetworking.get(Brain.MATCHES_URL_V2)
                    .addHeaders(Brain.AUTHORIZATION, Brain.TOKEN)
                    .build()
                    .getObjectObservable(MatchResponse::class.java)

    override fun makeTeamApiCall(): Observable<TeamResponse> =
            Rx2AndroidNetworking.get(Brain.TEAM_URL_V2)
                    .addHeaders(Brain.AUTHORIZATION, Brain.TOKEN)
                    .build()
                    .getObjectObservable(TeamResponse::class.java)

    override fun makeUpdateViewApiCall(): Observable<String> =
            Rx2AndroidNetworking.get(Brain.VIEWS_URL + Brain.newsId)
                    .build()
                    .stringObservable
}