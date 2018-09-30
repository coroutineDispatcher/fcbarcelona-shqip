package com.stavro_xhardha.fcbarcelonashqip.ui.ranking.view

import com.stavro_xhardha.fcbarcelonashqip.model.ranking.Standing
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.MVPView

interface RankingMVPView : MVPView {
    fun onRankingResponse(standing: Standing)
    fun showConnectionError()
}