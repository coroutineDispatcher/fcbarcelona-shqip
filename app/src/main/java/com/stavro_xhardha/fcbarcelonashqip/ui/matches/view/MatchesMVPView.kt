package com.stavro_xhardha.fcbarcelonashqip.ui.matches.view

import com.stavro_xhardha.fcbarcelonashqip.model.match.MatchResponse
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.MVPView

interface MatchesMVPView : MVPView {
    fun onMatchResponse(matches: MatchResponse)
}