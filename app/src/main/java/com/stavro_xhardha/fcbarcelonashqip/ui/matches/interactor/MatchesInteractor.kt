package com.stavro_xhardha.fcbarcelonashqip.ui.matches.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.match.MatchResponse
import com.stavro_xhardha.fcbarcelonashqip.network.AppApiHelper
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class MatchesInteractor @Inject constructor(apiHelper: AppApiHelper)
    : BaseInteractor(apiHelper = apiHelper), MatchesMVPInteractor {


    override fun getMatchesList(): Observable<MatchResponse> = apiHelper.makeMatchesApiCall()

}