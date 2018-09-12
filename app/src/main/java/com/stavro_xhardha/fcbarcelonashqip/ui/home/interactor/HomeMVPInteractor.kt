package com.stavro_xhardha.fcbarcelonashqip.ui.home.interactor

import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface HomeMVPInteractor : MVPInteractor {
    fun makeUpdateViewsCall(): Observable<String>
}