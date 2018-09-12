package com.stavro_xhardha.fcbarcelonashqip.ui.matches.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.match.MatchResponse
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface MatchesMVPInteractor : MVPInteractor {
    fun getMatchesList(): Observable<MatchResponse>
}