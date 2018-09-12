package com.stavro_xhardha.fcbarcelonashqip.ui.team.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.team.TeamResponse
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface TeamMvpInteractor : MVPInteractor {
    fun getPlayersList(): Observable<TeamResponse>
}