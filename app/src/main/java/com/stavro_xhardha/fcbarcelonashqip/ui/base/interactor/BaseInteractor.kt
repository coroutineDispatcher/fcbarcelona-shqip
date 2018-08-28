package com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor

import com.stavro_xhardha.fcbarcelonashqip.network.ApiHelper

/**
 * Created by jyotidubey on 04/01/18.
 */
open class BaseInteractor() : MVPInteractor {

    protected lateinit var apiHelper: ApiHelper

    constructor(apiHelper: ApiHelper) : this() {
        this.apiHelper = apiHelper
    }

}