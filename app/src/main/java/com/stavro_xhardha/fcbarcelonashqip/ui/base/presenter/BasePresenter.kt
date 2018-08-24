package com.stavro_xhardha.fcbarcelonashqip.ui.base.presenter


import com.stavro_xhardha.fcbarcelonashqip.brain.SchedulerProvider
import com.stavro_xhardha.fcbarcelonashqip.ui.base.interactor.MVPInteractor
import com.stavro_xhardha.fcbarcelonashqip.ui.base.view.MVPView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by jyotidubey on 04/01/18.
 */
abstract class BasePresenter<V : MVPView, I : MVPInteractor>
internal constructor(protected var interactor: I?, protected val schedulerProvider: SchedulerProvider,
                     protected val compositeDisposable: CompositeDisposable) : MVPPresenter<V, I> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }

}