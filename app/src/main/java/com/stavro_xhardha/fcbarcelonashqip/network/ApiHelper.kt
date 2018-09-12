package com.stavro_xhardha.fcbarcelonashqip.network

import com.stavro_xhardha.fcbarcelonashqip.model.NewsBody
import com.stavro_xhardha.fcbarcelonashqip.model.ranking.Standing
import com.stavro_xhardha.fcbarcelonashqip.model.Topic
import com.stavro_xhardha.fcbarcelonashqip.model.match.MatchResponse
import com.stavro_xhardha.fcbarcelonashqip.model.team.TeamResponse
import io.reactivex.Observable

interface ApiHelper {
    fun makeTopicApiCall(): Observable<List<Topic>>
    fun makeNewsBodyApiCall(): Observable<NewsBody>
    fun makeRankingApiCall(): Observable<Standing>
    fun makeMatchesApiCall(): Observable<MatchResponse>
    fun makeTeamApiCall(): Observable<TeamResponse>
    fun makeUpdateViewApiCall(): Observable<String>
}