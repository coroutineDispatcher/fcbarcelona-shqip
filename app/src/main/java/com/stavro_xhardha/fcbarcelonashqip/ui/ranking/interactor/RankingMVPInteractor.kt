package com.stavro_xhardha.fcbarcelonashqip.ui.ranking.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.ranking.Standing
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface RankingMVPInteractor : MVPInteractor {
    fun getLaLigaRanking(): Observable<Standing>
}