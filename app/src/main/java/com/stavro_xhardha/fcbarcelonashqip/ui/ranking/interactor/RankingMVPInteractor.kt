package com.stavro_xhardha.fcbarcelonashqip.ui.ranking.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.Standing
import com.stavro_xhardha.fcbarcelonashqip.model.StandingResponse
import com.stavro_xhardha.fcbarcelonashqip.model.TableItem
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface RankingMVPInteractor : MVPInteractor {
    fun getLaLigaRanking(): Observable<Standing>
}