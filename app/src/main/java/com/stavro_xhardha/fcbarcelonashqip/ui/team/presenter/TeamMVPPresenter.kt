package com.stavro_xhardha.fcbarcelonashqip.ui.team.presenter

import com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter.MVPPresenter
import com.stavro_xhardha.fcbarcelonashqip.ui.team.interactor.TeamMvpInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.team.view.TeamMvpView

interface TeamMVPPresenter<V: TeamMvpView ,I: TeamMvpInteractor> : MVPPresenter<V , I> {
    fun onViewPrepared(): Unit?
}