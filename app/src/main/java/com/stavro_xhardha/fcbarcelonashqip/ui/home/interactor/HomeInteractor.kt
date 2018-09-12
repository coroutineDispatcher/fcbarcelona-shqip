package com.stavro_xhardha.fcbarcelonashqip.ui.home.interactor

import com.stavro_xhardha.fcbarcelonashqip.network.AppApiHelper
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class HomeInteractor @Inject internal constructor(apiHelper: AppApiHelper) : BaseInteractor(apiHelper = apiHelper), HomeMVPInteractor {

    override fun makeUpdateViewsCall(): Observable<String> = apiHelper.makeUpdateViewApiCall()
}