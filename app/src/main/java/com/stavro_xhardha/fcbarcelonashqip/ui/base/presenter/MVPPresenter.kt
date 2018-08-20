package com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter

import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.MVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.MVPView

/**
 * Created by jyotidubey on 04/01/18.
 */
interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}