package com.stavro_xhardha.fcbarcelonashqip.ui.team.view

import com.stavro_xhardha.fcbarcelonashqip.model.team.SquadItem
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.MVPView

interface TeamMvpView : MVPView {
    fun addPlayersToList(squad: ArrayList<SquadItem?>?)
}