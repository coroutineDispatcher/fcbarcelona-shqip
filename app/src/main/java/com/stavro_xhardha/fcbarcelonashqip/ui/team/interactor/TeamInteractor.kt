package com.stavro_xhardha.fcbarcelonashqip.ui.team.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.team.TeamResponse
import com.stavro_xhardha.fcbarcelonashqip.network.AppApiHelper
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.BaseInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.MVPInteractor
import io.reactivex.Observable
import javax.inject.Inject

class TeamInteractor @Inject constructor(apiHelper: AppApiHelper) : BaseInteractor(apiHelper = apiHelper)
        , TeamMvpInteractor {

    override fun getPlayersList(): Observable<TeamResponse> = apiHelper.makeTeamApiCall()
}