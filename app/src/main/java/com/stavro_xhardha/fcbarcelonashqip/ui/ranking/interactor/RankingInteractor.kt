package com.stavro_xhardha.fcbarcelonashqip.ui.ranking.interactor

import com.stavro_xhardha.fcbarcelonashqip.model.Standing
import com.stavro_xhardha.fcbarcelonashqip.model.TableItem
import com.stavro_xhardha.fcbarcelonashqip.network.ApiHelper
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class RankingInteractor @Inject internal constructor(apiHelper: ApiHelper) : BaseInteractor(apiHelper = apiHelper)
        , RankingMVPInteractor {
    override fun getLaLigaRanking(): Observable<Standing> = apiHelper.makeRankingApiCall()
}